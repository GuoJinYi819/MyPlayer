package com.example.myplayer.ui.fragment

import android.view.View
import com.example.myplayer.R
import com.example.myplayer.base.BaseFragment

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:21
 * @Description: 用途：完成特定功能
 */
class MyPagerFragment:BaseFragment() {
    override fun initView(): View? {
        return View.inflate(context,R.layout.fargment_mypager,null)
    }
}