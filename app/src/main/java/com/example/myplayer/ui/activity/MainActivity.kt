package com.example.myplayer.ui.activity

import androidx.appcompat.widget.Toolbar
import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import com.example.myplayer.util.FragmentUtil
import com.example.myplayer.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager{
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun initLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

    override fun initListener() {
        //tab切换监听
        bottomBar.setOnTabSelectListener {
            //it代表参数
            //切换fragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
            transaction.commit()
        }
    }

}
