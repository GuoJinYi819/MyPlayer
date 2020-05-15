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
    var position:Int = -2  //正在播放的 position

    var mediaPlayer:MediaPlayer? = null


    companion object{
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }

    var mode = MODE_ALL //当前播放模式

    val binder by lazy { AudioBinder() }
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //获取集合
        list = intent?.getParcelableArrayListExtra<AudioBean>("list")
        val pos = intent?.getIntExtra("position", -1)?:-1  //想要播放position
        if(pos!=position){
            //开始播放
            position = pos
            binder.playItme()

        }else{
            //通知更新ui
            binder.notifyUpdataUi()

        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }


    inner class AudioBinder:Binder(),IService, MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {
        //上一曲
        override fun playPre() {
            list?.let {
                //获取要播放歌曲 position
                when(mode){
                    MODE_RANDOM ->position = Random.nextInt(it.size-2)
                    else->{
                        if(position==0){
                            position = it.size-1
                        }else{
                            position--
                        }
                    }
                }
            }
            playItme()
        }
        //下一曲
        override fun playNext() {
            list?.let {
                when(mode){
                    MODE_RANDOM->position = Random.nextInt(it.size-1)
                    else->{
                        position = (position+1)%it.size
                    }
                }
            }
            playItme()
        }

        //获取播放模式
        override fun getPlayMode(): Int {
            return mode
        }

        //修改播放模式
        override fun updatePlayMode() {
           when(mode){
               MODE_ALL -> mode = MODE_SINGLE
               MODE_SINGLE -> mode = MODE_RANDOM
               MODE_RANDOM -> mode = MODE_ALL
           }
        }

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

        public fun notifyUpdataUi() {
            //通知界面更新
            EventBus.getDefault().post(list?.get(position))
        }

        fun playItme(){
            //判断 mediaPlayer已经存在 就释放掉
            if (mediaPlayer != null) {
                //释放 mediaPlayer资源
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
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