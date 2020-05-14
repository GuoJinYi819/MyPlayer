package com.example.myplayer.presenter.impl

import com.example.myplayer.bean.MvBean
import com.example.myplayer.net.MvAreaRequest
import com.example.myplayer.net.ResponseHandler
import com.example.myplayer.presenter.interf.MVPresenter
import com.example.myplayer.view.MVView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:09
 * @Description: 用途：完成特定功能
 */
class MVPresenterImpl(var mvView:MVView):MVPresenter, ResponseHandler<MvBean> {
    override fun loadData() {
        MvAreaRequest(this)  // .execute  发送网络请求（没有接口）
    }

    override fun onError(type: Int, msg: String?) {
        mvView.onError(msg)
    }

    override fun onSuccess(type: Int, result: MvBean?) {
        mvView.onSuccess(result)
    }
}