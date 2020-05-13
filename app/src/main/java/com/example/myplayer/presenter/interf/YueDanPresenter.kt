package com.example.myplayer.presenter.interf

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 16:40
 * @Description: 用途：完成特定功能
 */
interface YueDanPresenter {
    companion object{
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }
    fun loadDatas() {

    }

    fun loadMore(i: Int) {

    }
}