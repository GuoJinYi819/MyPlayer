package com.example.myplayer.presenter.impl

import com.example.myplayer.bean.HomeBean
import com.example.myplayer.net.HomeRequest
import com.example.myplayer.net.NetManager
import com.example.myplayer.net.ResponseHandler
import com.example.myplayer.presenter.interf.HomePresenter
import com.example.myplayer.util.ThreadUtil
import com.example.myplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 13:45
 * @Description: 用途：完成特定功能
 */
class HomePresenterImpl(var homeView:HomeView?):HomePresenter, ResponseHandler<HomeBean> {

    fun destoryView(){
        if(homeView!=null){
            homeView = null
        }
    }

    //初始化数据/刷新
    override fun loadDatas() {
        //1 定义 request
        HomeRequest(HomePresenter.TYPE_INIT_OR_REFRESH,this).execute()
    }

    override fun loadMore(i: Int) {
        //1 定义 request
        HomeRequest(HomePresenter.TYPE_LOAD_MORE,this).execute()
    }

    override fun onError(type:Int,msg: String?) {
       homeView?.onError(msg)
    }

    override fun onSuccess(type:Int,result: HomeBean?) {
        //区分  初始化数据-加载数据
        when(type){
            HomePresenter.TYPE_INIT_OR_REFRESH->homeView?.loadSuccess(result)
            HomePresenter.TYPE_LOAD_MORE->homeView?.loadMore(result)
        }

    }
}