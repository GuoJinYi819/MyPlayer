package com.example.myplayer.ui.activity

import com.example.myplayer.R
import com.example.myplayer.base.BaseActivity
import com.example.myplayer.bean.AudioBean

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 14:04
 * @Description: 用途：完成特定功能
 */
class AudioPlayerActivity :BaseActivity(){
    override fun initLayoutId(): Int {
        return R.layout.activity_audio_player
    }

    override fun initData() {
        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
        val position = intent.getIntExtra("position", -1)
        println("list==$list")
        println("position==$position")
    }
}