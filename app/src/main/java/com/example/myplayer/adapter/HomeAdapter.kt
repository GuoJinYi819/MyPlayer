package com.example.myplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.bean.HomeBean
import com.example.myplayer.widget.HomeItemView
import com.example.myplayer.widget.LoadMoreView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 9:24
 * @Description: 用途：完成特定功能
 */
class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    //定义集合
    private var list = ArrayList<HomeBean.ResultBean>()
    fun updataList(list: List<HomeBean.ResultBean>){
        //保证list为空
        this.list.clear()
        //添加list
        this.list.addAll(list)
        //刷新适配器
        notifyDataSetChanged()

    }

    //加载更多   不需要清空之前集合
    fun loadMore(list:List<HomeBean.ResultBean>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun getItemViewType(position: Int): Int {
        if(position==list.size){
            //当前 position是最后一条
            return 1
        }else{
            return 0
        }
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        //如果是 最后一条就不需要设置参数了
        if(position==list.size){
            return
        }
        //获取数据
        val data = list.get(position)
        //获取布局                               转换类型
        val itemView = holder.itemView as HomeItemView

        //条目刷新
        itemView.setData(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        if(viewType==1){
            return HomeHolder(LoadMoreView(parent.context))
        }else{
            //普通条目
            return HomeHolder(HomeItemView(parent.context))
        }

    }

    class HomeHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}