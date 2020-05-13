package com.example.myplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.bean.YueDanBean
import com.example.myplayer.widget.YueDanItemView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 16:30
 * @Description: 用途：悦单界面
 */
class YueDanAdapter: RecyclerView.Adapter<YueDanAdapter.YueDanHolder>() {
    //接收数据
    private var list = ArrayList<YueDanBean.ResultBean>()

    //初始化集合
    fun updateList(list:List<YueDanBean.ResultBean>){
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
        return YueDanHolder(YueDanItemView(parent?.context))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {
        //data
        val data = list.get(position)
        val itemView = holder.itemView as YueDanItemView
        //绑定数据
        itemView.setData(data)

    }

    class YueDanHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }
}