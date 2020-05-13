package com.example.myplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.widget.YueDanItemView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 16:30
 * @Description: 用途：悦单界面
 */
class YueDanAdapter: RecyclerView.Adapter<YueDanAdapter.YueDanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return YueDanHolder(YueDanItemView(parent?.context))
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {
    }

    class YueDanHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }
}