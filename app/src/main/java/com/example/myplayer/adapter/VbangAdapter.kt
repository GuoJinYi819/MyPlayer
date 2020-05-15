package com.example.myplayer.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.cursoradapter.widget.CursorAdapter
import com.example.myplayer.bean.AudioBean
import com.example.myplayer.widget.VBangItemView


/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/15 0015 9:18
 * @Description: 用途：v榜界面 列表适配器
 */
class VbangAdapter(var context: Context?,var c:Cursor?): CursorAdapter(context,c) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        //
        return VBangItemView(context)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val itemView = view as VBangItemView
        //获取数据
        val itemBean = AudioBean.getAudioBean(cursor!!)
        //绑定数据
        itemView.setData(itemBean)
    }
}