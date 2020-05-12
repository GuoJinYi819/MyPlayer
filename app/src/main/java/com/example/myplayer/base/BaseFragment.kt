package com.example.myplayer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/12 0012 19:23
 * @Description: 用途：fragment基类
 */
abstract class BaseFragment :Fragment(),AnkoLogger{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    protected fun init() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initView()
    }

    //获取布局view
    abstract fun initView(): View?

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    //数据操作
    protected fun initData() {

    }
    //监听
    protected fun initListener() {

    }

    //吐司
    fun myToast(msg:String){
        context?.runOnUiThread { toast(msg) }
    }
}