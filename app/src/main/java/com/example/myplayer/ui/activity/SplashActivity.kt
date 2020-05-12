package com.example.myplayer.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/12 0012 19:26
 * @Description: 用途：引导页
 */
class SplashActivity:BaseActivity(), ViewPropertyAnimatorListener {
    //实现动画监听
    override fun onAnimationEnd(view: View?) {
        //进入主界面
        startActivity<MainActivity>()
        //销毁当前页面
        finish()
    }

    override fun onAnimationCancel(view: View?) {

    }

    override fun onAnimationStart(view: View?) {

    }

    override fun initLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        //       对哪一个view进行动画处理
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setDuration(2000).setListener(this)
    }

}