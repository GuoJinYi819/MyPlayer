package com.example.myplayer.ui.fragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplayer.R
import com.example.myplayer.adapter.MvAdapter
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.bean.MvPagerBean
import com.example.myplayer.presenter.impl.MVPresenterImpl
import com.example.myplayer.view.MVView
import kotlinx.android.synthetic.main.fargment_mypager.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:21
 * @Description: 用途：完成特定功能
 */
class MyPagerFragment:BaseFragment(), MVView {

    override fun loadMore(json: MvPagerBean?) {

    }

    override fun loadSuccess(json: MvPagerBean?) {
        adapter.updataList(json?.result)
    }

    override fun onError(message: String?) {
        println(message)
    }

    //惰性创建presenter
    val presenter by lazy { MVPresenterImpl(this) }

    val adapter by lazy { MvAdapter(context!!) }

    override fun initView(): View? {
        return View.inflate(context,R.layout.fargment_mypager,null)
    }

    override fun initListener() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        swipeRefresh.setColorSchemeColors(Color.BLUE,Color.YELLOW)
        swipeRefresh.setOnRefreshListener {
            presenter.loadData()
            myToast("刷新成功")
            swipeRefresh.isRefreshing = false
        }
    }

    override fun initData() {
        //获取数据
        presenter.loadData()
    }
}