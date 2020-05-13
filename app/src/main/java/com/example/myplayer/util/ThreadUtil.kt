package com.example.myplayer.util

import android.os.Handler
import android.os.Looper


/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 11:24
 * @Description: 用途：完成特定功能
 */
object ThreadUtil {
    //创建handler        创建 Looper对象
    val handler = Handler(Looper.getMainLooper())
    //允许在主线程中
    fun runOnMainThread(runnable:Runnable){
        handler.post(runnable)
    }
}