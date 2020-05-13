package com.example.myplayer.net

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 14:12
 * @Description: 用途：请求回调
 */
open class ResponseHandler<RESPONSE> {
    open fun onError(msg:String?){

    }
    open fun onSuccess(result:RESPONSE) {

    }
}