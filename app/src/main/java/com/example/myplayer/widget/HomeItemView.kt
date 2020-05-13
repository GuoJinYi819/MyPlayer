package com.example.myplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.myplayer.R
import com.example.myplayer.bean.HomeBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 9:07
 * @Description: 用途：完成特定功能
 */
class HomeItemView:RelativeLayout {


    //构造方法
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //初始化方法
    init {
        View.inflate(context, R.layout.item_home,this)
    }

    //刷新条目
    fun setData(data: HomeBean.ResultBean) {
        //歌曲名称
        title.setText("嘿嘿")
        //简介
        desc.setText("嘿嘿嘿")
        //加载图片
        Picasso.with(context).load(data.imageUrl).into(bg)

    }

}