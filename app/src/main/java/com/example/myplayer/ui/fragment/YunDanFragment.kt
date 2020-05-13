package com.example.myplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplayer.R
import com.example.myplayer.adapter.YueDanAdapter
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.bean.YueDanBean
import com.example.myplayer.presenter.impl.YueDanPresenterImpl
import com.example.myplayer.view.YueDanView
import kotlinx.android.synthetic.main.fragment_list.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 8:43
 * @Description: 用途：完成特定功能
 */
class YunDanFragment:BaseFragment(), YueDanView {
    //惰性加载 adapter
    val adapter by lazy { YueDanAdapter() }
    //创建 presenter
    val presenter by lazy { YueDanPresenterImpl(this) }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list,null)
    }

    override fun initListener() {
        //布局管理器
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun initData() {
        //加载数据
        presenter.loadDatas()
    }

    override fun onError(message: String?) {
        myToast("加载失败")
    }

    override fun loadSuccess(json: YueDanBean?) {
        //刷新适配器

    }

}