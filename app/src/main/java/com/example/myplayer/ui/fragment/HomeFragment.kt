package com.example.myplayer.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myplayer.R
import com.example.myplayer.adapter.HomeAdapter
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.bean.HomeBean
import com.example.myplayer.util.ThreadUtil
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
class HomeFragment:BaseFragment() {
    //惰性加载
    val adapter by lazy { HomeAdapter() }

    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_home,null)
    }

    override fun initListener() {
        //布局管理器
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter
    }

    override fun initData() {
        val path = "http://mobile.bwstudent.com/movieApi/tool/v2/banner"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(path)
            .get()
            .build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                //获取数据出错
                println("出错")
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
                        //刷新列表
                        adapter.updataList(json.result)
                    }

                })
            }

        })
    }
}