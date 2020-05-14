package com.example.myplayer.net

import com.example.myplayer.bean.MvBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 9:14
 * @Description: 用途：完成特定功能
 */
class MvAreaRequest(handler: ResponseHandler<MvBean>):MRequest<MvBean>(0,"",handler) {
}