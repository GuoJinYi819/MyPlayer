package com.example.myplayer.ui.activity

import android.preference.PreferenceActivity
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import androidx.appcompat.widget.Toolbar
import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import com.example.myplayer.util.ToolBarManager
import org.jetbrains.anko.find

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/12 0012 21:33
 * @Description: 用途：完成特定功能
 */
class SettingActivity:BaseActivity(),ToolBarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun initLayoutId(): Int {


        return R.layout.activity_setting;
    }

    override fun initData() {
        initSettingToolbar()
        //获取推送通知 有没有选中
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val push = sp.getBoolean("push", false)
        println(push)
    }
}