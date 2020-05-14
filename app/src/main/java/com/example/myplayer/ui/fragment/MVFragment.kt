package com.example.myplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.myplayer.R
import com.example.myplayer.adapter.MvPagerAdapter
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.bean.MvBean
import com.example.myplayer.bean.MvPagerBean
import com.example.myplayer.presenter.impl.MVPresenterImpl
import com.example.myplayer.view.MVView
import kotlinx.android.synthetic.main.fragment_mv.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 8:43
 * @Description: 用途：完成特定功能
 */
class MVFragment:BaseFragment(),MVView{



    override fun onError(msg: String?) {
        //失败了
    }

    //惰性加载 presenter
    val presenter by lazy { MVPresenterImpl(this) }
    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_mv,null)

    }

    override fun initListener() {

    }

    override fun initData() {
        //加载区域数据
        var list = ArrayList<MvBean>()
        list.add(MvBean("嘿嘿"))
        list.add(MvBean("呼呼"))
        list.add(MvBean("略略"))
        list.add(MvBean("气气"))
        list.add(MvBean("呜呜"))
        val adapter = MvPagerAdapter(list,childFragmentManager)
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)

    }
}