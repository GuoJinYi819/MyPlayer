package com.example.myplayer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myplayer.bean.MvBean
import com.example.myplayer.ui.fragment.MyPagerFragment

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:18
 * @Description: 用途：完成特定功能
 */
class MvPagerAdapter(val list:ArrayList<MvBean>,val fm:FragmentManager):FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return MyPagerFragment()
    }

    override fun getCount(): Int {
        //如果 集合不为空 返回size  为空 返回0
        return list?.size?:0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list.get(position).name
    }
}