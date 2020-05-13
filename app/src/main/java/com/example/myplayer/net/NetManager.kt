package com.example.myplayer.net

import com.example.myplayer.bean.HomeBean
import com.example.myplayer.util.ThreadUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 14:14
 * @Description: 用途：发送网络请求
 */
class NetManager private constructor(){
    val client  by lazy { OkHttpClient() }
    companion object{
        val manager by lazy { NetManager() }

    }
    //发送网络请求
    fun <RESPONSE> sendRequest(req:MRequest<RESPONSE>){
        //val path = ""

        val request = Request.Builder()
            .url(req.url)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //获取数据出错
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调到 view层处理
                        req.handler.onError(req.type,e?.message)
                    }

                })
            }

            override fun onResponse(call: Call, response: Response) {
                //获取数据成功
                val body = response.body
                //获取数据
                val result = body?.string()
                val parseResult = req.parseResult(result)

                //转主线程
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //回调到view层
                        req.handler.onSuccess(req.type,parseResult)
                    }

                })
            }

        })
    }
}