package com.example.myplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 19:16
 * @Description: 用途：完成特定功能
 */
class MarqueeTextView : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    override fun isFocused(): Boolean {
        return true
    }

}