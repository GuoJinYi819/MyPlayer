package com.example.myplayer.ui.activity

import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_player.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 11:23
 * @Description: 用途：完成特定功能
 */
class VideoPlayerActivity :BaseActivity(){
    override fun initLayoutId(): Int {
        return R.layout.activity_video_player
    }

    override fun initListener() {
        videoView.setVideoPath("http://mobile.bwstudent.com/video/movie/xbyz/xbyz2.ts") //异步准备
        //  videoView.start()  防止音频未加载完成 开启播放会报错
        videoView.setOnPreparedListener {
            //进行播放
            videoView.start()
        }
    }
}