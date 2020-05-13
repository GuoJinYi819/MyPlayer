package com.example.myplayer.bean

import android.media.Image


/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 10:58
 * @Description: 用途：首页数据bean类
 */
data class YueDanBean(var status:String, var message:String, var result:List<ResultBean>) {

    data class ResultBean(var imageUrl:String,var jumpUrl:String ){

    }
}