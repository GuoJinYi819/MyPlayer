package com.example.myplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.myplayer.base.BaseFragment

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 16:58
 * @Description: 用途：视频播放界面
 */
class DefaultFragment:BaseFragment() {
    override fun initView(): View? {
        val  tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.setTextColor(Color.BLUE)
        tv.text = "无内容"
        return tv
    }
}