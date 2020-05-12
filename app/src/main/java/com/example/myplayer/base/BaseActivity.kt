package com.example.myplayer.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/12 0012 19:19
 * @Description: 用途：Activity基类
 */
abstract class BaseActivity:AppCompatActivity(),AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayoutId())
        initListener()
        initData()

    }

    protected fun initData() {

    }

    protected fun initListener() {

    }

    //返回布局
    abstract fun initLayoutId(): Int

    //吐司
    protected fun myToast(msg:String){
        runOnUiThread { toast(msg) }
    }

}