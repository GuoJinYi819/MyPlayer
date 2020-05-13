package com.example.myplayer.presenter.impl

import com.example.myplayer.bean.HomeBean
import com.example.myplayer.presenter.interf.HomePresenter
import com.example.myplayer.util.ThreadUtil
import com.example.myplayer.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 13:45
 * @Description: 用途：完成特定功能
 */
class HomePresenterImpl(var homeView:HomeView):HomePresenter {

    //初始化数据/刷新
    override fun loadDatas() {
        val path = "http://mobile.bwstudent.com/movieApi/tool/v2/banner"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //获取数据出错
                println("出错"+e.message)
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调到 view层处理
                        homeView.onError(e?.message)
                    }

                })
            }

            override fun onResponse(call: Call, response: Response) {
                //获取数据成功
                val body = response.body
                //获取数据
                val result = body?.string()
                //解析
                val gson = Gson()
                var json = gson.fromJson<HomeBean>(result,object : TypeToken<HomeBean>(){}.type)

                //转主线程
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调到view层
                        homeView.loadSuccess(json)
                    }

                })
            }

        })
    }

    override fun loadMore(i: Int) {
        val path = "http://mobile.bwstudent.com/movieApi/tool/v2/banner"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                //获取数据出错
                println("出错"+e.message)
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调
                        homeView.onError(e.message)
                    }

                })
            }

            override fun onResponse(call: Call, response: Response) {
                //获取数据成功
                val body = response.body
                //获取数据
                val result = body?.string()
                //解析
                val gson = Gson()
                var json = gson.fromJson<HomeBean>(result,object :TypeToken<HomeBean>(){}.type)

                //转主线程
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调
                        homeView.loadMore(json)
                    }

                })
            }

        })
    }


}