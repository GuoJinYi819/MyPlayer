package com.example.myplayer.presenter.impl

import com.example.myplayer.bean.MvPagerBean
import com.example.myplayer.net.MvAreaRequest
import com.example.myplayer.net.ResponseHandler
import com.example.myplayer.presenter.interf.HomePresenter
import com.example.myplayer.presenter.interf.MVPresenter
import com.example.myplayer.view.MVView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:09
 * @Description: 用途：完成特定功能
 */
class MVPresenterImpl(var mvView:MVView?):MVPresenter, ResponseHandler<MvPagerBean> {
    fun destoryView(){
        if(mvView!=null){
            mvView = null
        }
    }
    override fun loadData() {
        MvAreaRequest(MVPresenter.TYPE_INIT_OR_REFRESH,this).execute()  //发送网络请求（没有接口）
    }

    override fun onError(type: Int, msg: String?) {
        mvView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: MvPagerBean?) {
        when(type){
            HomePresenter.TYPE_INIT_OR_REFRESH->mvView?.loadSuccess(result)
            HomePresenter.TYPE_LOAD_MORE->mvView?.loadMore(result)
        }
    }
}