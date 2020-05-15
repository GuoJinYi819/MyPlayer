package com.example.myplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.myplayer.bean.AudioBean
import org.greenrobot.eventbus.EventBus
import kotlin.random.Random

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 17:52
 * @Description: 用途：完成特定功能
 */
class AudioService :Service(){
    var list:ArrayList<AudioBean>? = null
    var position:Int = 0

    var mediaPlayer:MediaPlayer? = null

    val MODE_ALL = 1
    val MODE_SINGLE = 2
    val MODE_RANDOM = 3
    val mode = MODE_ALL //当前播放模式

    val binder by lazy { AudioBinder() }
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //获取集合
        list = intent?.getParcelableArrayListExtra<AudioBean>("list")
        position = intent?.getIntExtra("position", -1)?:-1
        //开始播放
        binder.playItme()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }


    inner class AudioBinder:Binder(),IService, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {
        //歌曲播放完成之后回调
        override fun onCompletion(mp: MediaPlayer?) {
            //自动播放下一首
            autoPlayNext()

        }
        //根据播放模式自动播放下一曲
        private fun autoPlayNext() {
            when(mode){
                MODE_ALL->{
                   list?.let {
                       position = (position+1)% it.size
                   }
                }
                MODE_SINGLE->{}
                MODE_RANDOM->list?.let {
                    position = Random.nextInt(it.size)
            }
            }
            playItme()
        }

        override fun seekTo(progress: Int) {
            mediaPlayer?.seekTo(progress)
        }

        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition?:0
        }

        override fun getDuration(): Int {
            return mediaPlayer?.duration?:0
        }

        override fun isPlaying(): Boolean{
            return mediaPlayer?.isPlaying!!
        }

        //更新播放状态
        override fun updatePlayStart() {
            //获取当前播放状态
            val isPlaying = isPlaying()
            isPlaying?.let {
                if(isPlaying){
                    //正在播放
                    mediaPlayer?.pause() //暂停
                }else{
                    //没有播放
                    mediaPlayer?.start() //开始
                }
            }
            //切换播放状态
        }

        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer?.start()
            //通知界面更新
            notifyUpdataUi()
        }

        private fun notifyUpdataUi() {
            //通知界面更新
            EventBus.getDefault().post(list?.get(position))
        }

        fun playItme(){
            mediaPlayer = MediaPlayer()
            //播放音乐
            mediaPlayer?.let {
                it.setOnPreparedListener(this)
                it.setOnCompletionListener(this)
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }

        }
    }


}