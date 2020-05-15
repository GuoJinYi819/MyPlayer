package com.example.myplayer.service

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 18:10
 * @Description: 用途：完成特定功能
 */
interface IService {
    fun isPlaying():Boolean
    fun updatePlayStart()
    fun getDuration(): Int
    fun getProgress(): Int
    fun seekTo(progress: Int)
    fun updatePlayMode()
    fun getPlayMode(): Int
    fun playPre()
    fun playNext()
}