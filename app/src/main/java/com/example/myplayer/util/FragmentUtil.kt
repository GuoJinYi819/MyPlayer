package com.example.myplayer.util

import androidx.fragment.app.Fragment
import com.example.myplayer.R
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.ui.fragment.HomeFragment
import com.example.myplayer.ui.fragment.MVFragment
import com.example.myplayer.ui.fragment.VBangFragment
import com.example.myplayer.ui.fragment.YunDanFragment

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 8:46
 * @Description: 用途：管理Fragment的Util类
 */
class FragmentUtil  private constructor(){ //构造方法私有化
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MVFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YunDanFragment() }




    //伴随对象  类名直接调用里面的属性和方法
    companion object{
        //惰性加载    by lazy是线程安全的操作
        val fragmentUtil by lazy { FragmentUtil() }
    }
    //根据 tabId 获取对应的fragment
    fun getFragment(tabId:Int):BaseFragment?{
        when(tabId){
            R.id.tab_home->return homeFragment
            R.id.tab_mv->return mvFragment
            R.id.tab_vbang->return vbangFragment
            R.id.tab_yuedan->return yuedanFragment
        }
        return null
    }
}