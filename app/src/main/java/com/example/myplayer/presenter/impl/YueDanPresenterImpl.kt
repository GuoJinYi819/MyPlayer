package com.example.myplayer.presenter.impl

import com.example.myplayer.bean.YueDanBean
import com.example.myplayer.net.ResponseHandler
import com.example.myplayer.net.YueDanRequest
import com.example.myplayer.presenter.interf.YueDanPresenter
import com.example.myplayer.view.YueDanView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 16:40
 * @Description: 用途：完成特定功能
 */
class YueDanPresenterImpl(var yueDanView:YueDanView):YueDanPresenter, ResponseHandler<YueDanBean> {
    override fun loadDatas() {
        YueDanRequest(YueDanPresenter.TYPE_INIT_OR_REFRESH,
            "http://mobile.bwstudent.com/movieApi/tool/v2/banner",this).execute()
    }

    override fun loadMore(i: Int) {
        YueDanRequest(YueDanPresenter.TYPE_LOAD_MORE,
            "http://mobile.bwstudent.com/movieApi/tool/v2/banner",this).execute()
    }

    override fun onError(type: Int, msg: String?) {
        yueDanView.onError(msg)
    }

    override fun onSuccess(type: Int, result: YueDanBean?) {
        if(type == YueDanPresenter.TYPE_INIT_OR_REFRESH){
            yueDanView.loadSuccess(result)
        }else{
            yueDanView.loadMore(result)
        }
    }

}