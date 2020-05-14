package com.example.myplayer.view

import com.example.myplayer.bean.HomeBean
import com.example.myplayer.bean.MvBean
import com.example.myplayer.bean.MvPagerBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:09
 * @Description: 用途：完成特定功能
 */
interface MVView {
    //获取数据失败
    fun onError(message: String?) {

    }
    //初始化数据/刷新数据
    fun loadSuccess(json: MvPagerBean?) {

    }
    //加载更多数据
    fun loadMore(json: MvPagerBean?) {

    }
}