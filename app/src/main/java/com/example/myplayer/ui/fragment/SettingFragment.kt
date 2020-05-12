package com.example.myplayer.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myplayer.R
import com.example.myplayer.ui.activity.AboutActivity
import org.jetbrains.anko.act

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/12 0012 21:49
 * @Description: 用途：完成特定功能
 */
@Suppress("DEPRECATION")
class SettingFragment: PreferenceFragment() {
    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        val key = preference?.key
        if("about".equals(key)){
           activity.startActivity(Intent(activity,AboutActivity::class.java))
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }

}