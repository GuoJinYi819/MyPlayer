package com.example.myplayer.net

import com.example.myplayer.bean.YueDanBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 16:43
 * @Description: 用途：悦单界面请求 request
 */
class YueDanRequest(type1:Int,url:String,handler:ResponseHandler<YueDanBean>):MRequest<YueDanBean>(type1,url,handler) {

}