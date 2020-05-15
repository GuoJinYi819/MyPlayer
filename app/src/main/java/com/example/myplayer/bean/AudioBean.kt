package com.example.myplayer.bean

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import java.util.ArrayList

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 9:30
 * @Description: 用途：音乐条目bean类
 */
@SuppressLint("ParcelCreator")
data class AudioBean(var data:String, var size:Long, var display_name:String, var artist:String):Parcelable{
    constructor(parcel: Parcel) : this(parcel.readString()!!, parcel.readLong()!!, parcel.readString()!!, parcel.readString()!!) {

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeLong(size)
        parcel.writeString(display_name)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioBean> {
        override fun createFromParcel(parcel: Parcel): AudioBean {
            return AudioBean(parcel)
        }

        override fun newArray(size: Int): Array<AudioBean?> {
            return arrayOfNulls(size)
        }

        //根据 特定位置cursor 获取bean类
        fun getAudioBean(cursor: Cursor):AudioBean {
            //创建 AudioBean对象
            val audio = AudioBean("", 0, "", "")
            //判断 cursor 是否为空
            cursor?.let {
                //解析
                //设置到bean对象中
                audio.data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                audio.size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE))
                audio.display_name =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                //截取  不要后缀
                audio.display_name =
                    audio.display_name.substring(0, audio.display_name.lastIndexOf("."))
                audio.artist =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
            }
            return audio
        }

        //根据特定位置 cursor 获取整个播放列表
        fun getAudioBeans(cursor: Cursor?): ArrayList<AudioBean> {
            // 1 创建集合
            val list = ArrayList<AudioBean>()
            //corsor是否为空
            cursor?.let {
                //将 cursor游标 移动到-1
                it.moveToPosition(-1)
                //解析 cursor 添加到集合中
                while (it.moveToNext()){
                    val audioBean = getAudioBean(cursor)
                    list.add(audioBean)
                }
            }
            //解析cursor添加到集合中
            return list
        }

    }
}