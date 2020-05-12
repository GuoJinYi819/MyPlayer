package com.example.myplayer.ui.activity

import androidx.appcompat.widget.Toolbar
import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import com.example.myplayer.util.ToolBarManager
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolBarManager{
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun initLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        initMainToolBar()
    }

}
