package com.example.myplayer.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.myplayer.R
import com.example.myplayer.bean.AudioBean
import kotlinx.android.synthetic.main.item_vbang.view.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 9:00
 * @Description: 用途：完成特定功能
 */
class VBangItemView:RelativeLayout {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    init {
        View.inflate(context, R.layout.item_vbang,this)
    }

    //数据和视图的绑定
    fun setData(itemBean: AudioBean) {
        //歌曲名
        title.text = itemBean.display_name
        //歌手名
        artist.text = itemBean.artist
        //歌曲大小     转换为 MB
        size.text = Formatter.formatFileSize(context,itemBean.size)

    }
}