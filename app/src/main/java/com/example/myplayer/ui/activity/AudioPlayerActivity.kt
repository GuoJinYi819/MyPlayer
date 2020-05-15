package com.example.myplayer.ui.activity

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.SeekBar
import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import com.example.myplayer.bean.AudioBean
import com.example.myplayer.service.AudioService
import com.example.myplayer.service.IService
import com.example.myplayer.util.StringUtil
import kotlinx.android.synthetic.main.activity_audio_player.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 14:04
 * @Description: 用途：完成特定功能
 */
class AudioPlayerActivity :BaseActivity(), View.OnClickListener {
    var audioBean:AudioBean? = null
    var animation:Animation? = null
    var duration:Int = 0
    val MSG_PROGRESS:Int = 0
    var handler = object :Handler(){
        override fun handleMessage(msg: Message?) {
            when(msg?.what){
               MSG_PROGRESS->startUpdateProgress()
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.start->updataPlayer()//更新播放状态
        }
    }

    //更新播放状态
    private fun updataPlayer() {
        //更新播放状态
        iService?.updatePlayStart()
        //更新播放状态图标
        updataPlayerStartBtn()
    }

    //根据播放状态更新图标
    private fun updataPlayerStartBtn() {
        //获取当前状态
        //根据状态更新图标
        val isPlayer = iService?.isPlaying()
        isPlayer?.let {
            if(isPlayer){
                //正在播放
                ivAction.startAnimation(animation)
                start.text = "暂停"
                //开始更新进度
                handler.sendEmptyMessageDelayed(MSG_PROGRESS,1000)
            }else{
                //没有播放
                start.text = "播放"
                ivAction.clearAnimation()
                //停止更新进度
                handler.removeMessages(MSG_PROGRESS)
            }
        }
    }

    val audioConnection by lazy { AudioConnection() }

    override fun initLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    override fun initListener() {

        //设置动画
        animation = AnimationUtils.loadAnimation(this,R.anim.img_action)
        val lin = LinearInterpolator()
        animation?.setInterpolator(lin)
        ivAction.startAnimation(animation)


        //销毁界面
        ivBlack.setOnClickListener {
            finish()
        }
        //开始
        start.setOnClickListener(this)

        //进度条变化 监听
        progress_sk.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //判断 是否是用户操作 造成的进度改变
                if(!fromUser) return
                //更新播放进度
                iService?.seekTo(progress)
                //更新界面进度显示
                updateProgresss(progress)
            }

        })
        //播放模式
        mode.setOnClickListener{
            updataPlayerMode()
        }
        //上一曲 下一曲
        pre.setOnClickListener {
            iService?.playPre()
        }
        next.setOnClickListener {
            iService?.playNext()
        }
    }

    //更新播放模式
    private fun updataPlayerMode() {
        //修改 service 中的模式
        iService?.updatePlayMode()
        //修改 界面模式图标

        updatePlayModeBtn()
    }

    //根据播放模式 修改播放模式图标
    private fun updatePlayModeBtn() {
        //获取播放模式
       iService?.let {
           val mode1:Int = it.getPlayMode()
           //设置图标
           when(mode1){
               AudioService.MODE_ALL->mode.text = "顺序播放"
               AudioService.MODE_SINGLE->mode.text = "单曲循环"
               AudioService.MODE_RANDOM->mode.text = "随机播放"
           }
       }


    }

    //接收 EventBus信息
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    fun onEventMain(itemBean:AudioBean){
        this.audioBean = itemBean
        //歌曲名
        tvName.text = itemBean.display_name


        //获取总进度
        duration = iService?.getDuration()?:0
        //设置进度条最大值
        progress_sk.max = duration
        //更新播放进度
        startUpdateProgress()
        //更新播放模式图标
        updatePlayModeBtn()
    }

    //开始更新进度
    private fun startUpdateProgress() {
        //获取当前进度
        val progress:Int = iService?.getProgress()?:0
        //更新进度数据
        updateProgresss(progress)
        //定时获取进度
        handler.sendEmptyMessageDelayed(MSG_PROGRESS,1000)
    }

    //根据当前进度数据 更新界面
    private fun updateProgresss(pro: Int) {
        //更新进度数值
        val dur = StringUtil.parseDuration(pro)
        time1.text = dur+"/"+StringUtil.parseDuration(duration)

        //更新进度条
        progress_sk.setProgress(pro)
    }


    override fun initData() {
        //注册EventBus
        EventBus.getDefault().register(this)

        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
        val position = intent.getIntExtra("position", -1)

        //开启服务 播放
        val intent = Intent(this,AudioService::class.java)
        intent.putExtra("list",list)
        intent.putExtra("position",position)

        //再绑定
        bindService(intent,audioConnection,Context.BIND_AUTO_CREATE)
        //先开启
        startService(intent)

//        //播放音乐
//        val mediaPlayer = MediaPlayer()
//
//        mediaPlayer.setOnPreparedListener {
//            //开始
//            mediaPlayer.start()
//        }
//        mediaPlayer.setDataSource(list.get(position).data)
//        mediaPlayer.prepareAsync()

    }

    var iService:IService? = null

    inner class AudioConnection:ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            //意外断开连接时
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //service连接超时
            iService = service as IService
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //手动解绑服务
        unbindService(audioConnection)
        //反注册EventBus
        EventBus.getDefault().unregister(this)

        //清空handler中 发送的所有消息
        handler.removeCallbacksAndMessages(null)
    }

}