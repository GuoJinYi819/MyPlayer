package com.example.myplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.widget.HomeItemView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 9:24
 * @Description: 用途：完成特定功能
 */
class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeHolder>() {


    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent.context))
    }

    class HomeHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}