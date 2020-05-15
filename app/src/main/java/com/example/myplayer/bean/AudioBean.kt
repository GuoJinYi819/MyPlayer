package com.example.myplayer.bean

import android.database.Cursor
import android.provider.MediaStore

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 9:30
 * @Description: 用途：音乐条目bean类
 */
data class AudioBean(var data:String,var size:Long,var display_name:String,var artist:String) {
    //伴随对象
    companion object{
        //根据 特定位置cursor 获取bean类
        fun getAudioBean(cursor: Cursor):AudioBean{
            //创建 AudioBean对象
            val audio = AudioBean("",0,"","")
            //判断 cursor 是否为空
            cursor?.let {
                //解析
                //设置到bean对象中
                audio.data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                audio.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audio.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                //截取  不要后缀
                audio.display_name = audio.display_name.substring(0,audio.display_name.lastIndexOf("."))
                audio.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            }
            return audio
        }
    }
}