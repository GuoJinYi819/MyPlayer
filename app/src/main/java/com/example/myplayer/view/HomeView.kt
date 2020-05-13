package com.example.myplayer.view

import com.example.myplayer.bean.HomeBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 13:45
 * @Description: 用途：负责home界面与 presenter交互
 */
interface HomeView {
    //获取数据失败
    fun onError(message: String?) {

    }
    //初始化数据/刷新数据
    fun loadSuccess(json: HomeBean?) {

    }
    //加载更多数据
    fun loadMore(json: HomeBean?) {

    }

}