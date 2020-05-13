package com.example.myplayer.net

import com.example.myplayer.bean.HomeBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 14:36
 * @Description: 用途：完成特定功能
 */
class HomeRequest(type:Int,handler: ResponseHandler<HomeBean>):
    MRequest<HomeBean>(type,"http://mobile.bwstudent.com/movieApi/tool/v2/banner",handler) {

}