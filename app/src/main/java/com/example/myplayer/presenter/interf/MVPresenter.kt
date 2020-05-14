package com.example.myplayer.presenter.interf

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:09
 * @Description: 用途：完成特定功能
 */
interface MVPresenter {
    companion object{
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE = 2
    }
    fun loadData() {

    }

    fun loadMore(i: Int) {

    }
}