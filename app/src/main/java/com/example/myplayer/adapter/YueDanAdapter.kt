package com.example.myplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.bean.YueDanBean
import com.example.myplayer.widget.LoadMoreView
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

    fun loadMore(list: List<YueDanBean.ResultBean>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YueDanHolder {
       if(viewType==1){
           return YueDanHolder(LoadMoreView(parent?.context))

       }else{
           //普通控件
           return YueDanHolder(YueDanItemView(parent?.context))
       }
    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun getItemViewType(position: Int): Int {
        if(position == list.size){
            return 1
        }else{
            //普通控件
            return 0
        }
    }

    override fun onBindViewHolder(holder: YueDanHolder, position: Int) {
        //加载更多条目 不做处理
       if(position == list.size){
           return
       }
        //data
        val data = list.get(position)
        val itemView = holder.itemView as YueDanItemView
        //绑定数据
        itemView.setData(data)

    }

    class YueDanHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    }
}