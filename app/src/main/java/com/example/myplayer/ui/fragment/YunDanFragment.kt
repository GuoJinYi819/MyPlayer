package com.example.myplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        //初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.YELLOW,Color.BLUE)
        //下拉刷新监听
        refreshLayout.setOnRefreshListener {
            presenter.loadDatas()
        }

        //监听列表滑动
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //当前列表状态为 空闲状态
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    //判断当前显示条目是否为最后一条
                    val layoutManager = recyclerView.layoutManager
                    //不是显示的一个列表
                    if(!(layoutManager is LinearLayoutManager)){
                        return
                    }
                    //显示的是列表
                    //获取最后一个item    kotlin智能类型转换
                    val lostPos = layoutManager.findLastVisibleItemPosition()
                    if(lostPos==adapter.itemCount-1){
                        //加载更多 刷新
                        presenter.loadMore(1)
                    }
                }
            }
        })
    }

    override fun initData() {
        //加载数据
        presenter.loadDatas()

    }

    override fun onError(message: String?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        myToast("加载失败")
    }

    override fun loadSuccess(json: YueDanBean?) {
        //隐藏刷新控件
        refreshLayout.isRefreshing = false
        myToast("加载数据成功")
        //刷新适配器
        adapter.updateList(json!!.result)
    }

    override fun loadMore(json: YueDanBean?) {
        adapter.loadMore(json!!.result)
    }

}