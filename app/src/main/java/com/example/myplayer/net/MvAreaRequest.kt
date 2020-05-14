package com.example.myplayer.net

import com.example.myplayer.bean.MvBean
import com.example.myplayer.bean.MvPagerBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:14
 * @Description: 用途：完成特定功能
 */
class MvAreaRequest(type:Int, handler:ResponseHandler<MvPagerBean>):MRequest<MvPagerBean>(type,"http://mobile.bwstudent.com/movieApi/tool/v2/banner",handler) {

}