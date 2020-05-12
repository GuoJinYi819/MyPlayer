package com.example.myplayer.util

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.myplayer.R
import com.example.myplayer.ui.activity.SettingActivity


/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/12 0012 21:01
 * @Description: 用途：完成特定功能
 */
interface ToolBarManager {
    val toolbar: Toolbar
    //初始化 主界面的toolbar
    fun initMainToolBar(){
        toolbar.setTitle("我的影音")
        toolbar.inflateMenu(R.menu.main)

        //如果java接口中 只有一个未实现的对象 可以直接省略接口对象 直接{ }表示未实现的方法
//        toolbar.setOnMenuItemClickListener{
//
//            true
//        }
        //与上边作用相同
        toolbar.setOnMenuItemClickListener(object :Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.setting->{
                        //跳转到指定界面
                        toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
                    }
                }
                return true
            }
        })
    }
    //设置界面的 toolbar
    fun initSettingToolbar(){
        toolbar.setTitle("设置界面")
    }
}