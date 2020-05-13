package com.example.myplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.myplayer.R
import com.example.myplayer.bean.YueDanBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_yuedan.view.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 16:22
 * @Description: 用途：悦单界面每个条目的自定义view
 */
class YueDanItemView:RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init{
        View.inflate(context,R.layout.item_yuedan,this)
    }

    //view条目的初始化
    fun setData(data: YueDanBean.ResultBean) {
        //歌单名
        author_title.text = "机器灵"
        //歌手名
        author_name.setText("呦呵和")
        //歌曲个数
        count.setText("1")
        //图片
        Picasso.with(context).load(data.imageUrl).into(author_image)
    }

}