package com.example.myplayer.ui.activity

import android.view.ViewParent
import androidx.viewpager.widget.ViewPager
import com.example.myplayer.base.BaseActivity
import com.example.myplayer.R
import com.example.myplayer.adapter.VideoPagerAdapter
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
        //适配viewPager
        viewPager.adapter = VideoPagerAdapter(supportFragmentManager)
        //radiogroup 监听
        rg.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb1->viewPager.setCurrentItem(0)
                R.id.rb2->viewPager.setCurrentItem(1)
                R.id.rb3->viewPager.setCurrentItem(2)
            }

        }
        viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            //滑动状态改变
            override fun onPageScrollStateChanged(state: Int) {

            }
            //滑动
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            //选中状态改变
            override fun onPageSelected(position: Int) {
                when(position){
                    0->rg.check(R.id.rb1)
                    1->rg.check(R.id.rb2)
                    2->rg.check(R.id.rb3)
                }
            }
        })

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