package com.example.myplayer.util

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 20:48
 * @Description: 用途：完成特定功能
 */
object StringUtil {
    val HOUR = 60*60*100
    val MIN = 60*1000
    val SEC = 1000
    fun parseDuration(process: Int):String{
        val hour = process/ HOUR
        val min = process % HOUR / MIN
        val sec = process % MIN / SEC
        var result:String = ""
        if(hour == 0){
            result = String.format("%02d:%02d",min,sec)
        }else{
            result = String.format("%02d:%02d%02d",hour,min,sec)
        }
        return result
    }
}