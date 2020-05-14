package com.example.myplayer.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.android.synthetic.main.activity_video_player_textture.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 11:23
 * @Description: 用途：完成特定功能
 */
class TextTureVideoPlayerActivity :BaseActivity(), TextureView.SurfaceTextureListener {
    var path ="http://mobile.bwstudent.com/video/movie/xbyz/xbyz2.ts"
    //惰性加载
    val mediaPlayer by lazy { MediaPlayer() }
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        //view 大小发生变化
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        //试图更新
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        //关闭 mediaplayer
        mediaPlayer?.let {
            mediaPlayer.stop()
            mediaPlayer.release()

        }


        //试图销毁
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        //试图 可用

        mediaPlayer.setDataSource(path)
        mediaPlayer.setSurface(Surface(surface)) //设置视频播放画面
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()//开始播放
            //旋转画面
            textureView.rotation =180f
        }
    }

    override fun initLayoutId(): Int {
        return R.layout.activity_video_player_textture
    }

    override fun initListener() {

        textureView.surfaceTextureListener = this


    }
}