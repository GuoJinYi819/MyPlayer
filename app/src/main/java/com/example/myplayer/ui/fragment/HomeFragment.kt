package com.example.myplayer.ui.fragment

import android.graphics.Color
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.R
import com.example.myplayer.adapter.HomeAdapter
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.bean.HomeBean
import com.example.myplayer.presenter.impl.HomePresenterImpl
import com.example.myplayer.util.ThreadUtil
import com.example.myplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 8:43
 * @Description: 用途：完成特定功能
 */
class HomeFragment:BaseFragment(), HomeView {
    //接口回调
    override fun onError(message: String?) {
        myToast("加载数据失败")
    }

    override fun loadSuccess(json: HomeBean?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        //刷新列表
        adapter.updataList(json?.result)
    }

    override fun loadMore(json: HomeBean?) {
        adapter.loadMore(json?.result)
    }

    //惰性加载
    val adapter by lazy { HomeAdapter() }

    val presenter by lazy { HomePresenterImpl(this) }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        //布局管理器
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter
        //初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED,Color.BLACK,Color.BLUE,Color.YELLOW)

        //上拉刷新监听
        refreshLayout.setOnRefreshListener {
            //获取数据
            presenter.loadDatas()
        }

        //监听列表的滑动
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    //空闲状态 且滑动到最后
                    //是否最后一条
                    val layoutManager = recyclerView.layoutManager
                    if(layoutManager is LinearLayoutManager){
                        //转换类型
                        val manager:LinearLayoutManager = layoutManager
                        val lastPosition = manager.findLastVisibleItemPosition()
                        if(lastPosition==adapter.itemCount-1){
                            //开始加重数据
                            presenter.loadMore(1)

                        }
                    }

                }

            }

        })

    }

    override fun initData() {
        presenter.loadDatas()
    }


}