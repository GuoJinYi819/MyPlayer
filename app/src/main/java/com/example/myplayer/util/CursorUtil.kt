package com.example.myplayer.util

import android.database.Cursor

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/14 0014 17:32
 * @Description: 用途：完成特定功能
 */
object CursorUtil {
    fun logCursor(cursor:Cursor){
        cursor?.let {
            //将 cursor游标复位
            it.moveToPosition(-1)
            while (it.moveToNext()){
                for (index in 0 until it.columnCount){
                    println("key = ${it.getColumnName(index)} ----value=${it.getString(index)}")
                }
            }
        }
    }
}