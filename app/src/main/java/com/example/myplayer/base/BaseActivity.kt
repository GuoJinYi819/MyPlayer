package com.example.myplayer.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myplayer.ui.activity.MainActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
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

    open protected fun initData() {

    }

    open protected fun initListener() {

    }

    //返回布局
    abstract fun initLayoutId(): Int

    //吐司
    protected fun myToast(msg:String){
        runOnUiThread { toast(msg) }
    }

    //开启一个activity 并且销毁当前界面
    //reified 关键字 指定 泛型T 上限为BaseActivity的子类
    inline fun <reified T:BaseActivity>startActivityAndFinish(){
        startActivity<T>()
        //销毁当前页面
        finish()
    }

}