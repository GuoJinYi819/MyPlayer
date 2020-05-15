package com.example.myplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.myplayer.bean.AudioBean
import org.greenrobot.eventbus.EventBus

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 17:52
 * @Description: 用途：完成特定功能
 */
class AudioService :Service(){
    var list:ArrayList<AudioBean>? = null
    var position:Int = 0

    var mediaPlayer:MediaPlayer? = null

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


    inner class AudioBinder:Binder(),IService, MediaPlayer.OnPreparedListener {
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
                it.setDataSource(list?.get(position)?.data)
                it.prepareAsync()
            }

        }
    }


}