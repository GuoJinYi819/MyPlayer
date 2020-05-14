package com.example.myplayer.ui.activity

import com.example.myplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_player.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.myplayer.R
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*


/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 11:23
 * @Description: 用途：节操播放器
 */
class JieCaoVideoPlayerActivity :BaseActivity(){
    override fun initLayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }

    override fun initListener() {

    }

    override fun initData() {
        val data = intent.data?.path
        if(data == null){
            jzvideo.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"嘿嘿嘿")
        }else{
            jzvideo.setUp(data.toString(),
                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,data.toString())
        }

        //  videoView.setVideoPath("http://mobile.bwstudent.com/video/movie/xbyz/xbyz2.ts") //异步准备
        //设置 播放路径 标题

    }

    override fun onBackPressed() {
        if(JCVideoPlayer.backPress()){
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        //清除资源
        JCVideoPlayer.releaseAllVideos()
    }

}