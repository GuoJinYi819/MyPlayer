package com.example.myplayer.net

import com.example.myplayer.bean.HomeBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 14:12
 * @Description: 用途：请求回调
 */
interface ResponseHandler<RESPONSE> {
     open fun onError(type:Int,msg:String?){

    }
    open fun onSuccess(type:Int,result:RESPONSE?) {

    }
}