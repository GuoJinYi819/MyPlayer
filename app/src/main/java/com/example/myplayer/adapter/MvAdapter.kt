package com.example.myplayer.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myplayer.bean.MvPagerBean
import com.example.myplayer.ui.activity.TextTureVideoPlayerActivity
import com.example.myplayer.ui.activity.VideoPlayerActivity
import com.example.myplayer.widget.LoadMoreView
import com.example.myplayer.widget.MvItemView

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 9:24
 * @Description: 用途：完成特定功能
 */
class MvAdapter(var context: Context): RecyclerView.Adapter<MvAdapter.MvHolder>() {
    //定义集合
    private var list = ArrayList<MvPagerBean.ResultBean>()


    fun updataList(list: List<MvPagerBean.ResultBean>?){
        //let表达式 相当于 对lift判空
        list?.let {
            //保证list为空
            this.list.clear()
            //添加list
            this.list.addAll(list)
            //刷新适配器
            notifyDataSetChanged()

        }


    }

    //加载更多   不需要清空之前集合
    fun loadMore(list:List<MvPagerBean.ResultBean>?){
        //相当于 对lift判空
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }

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


    override fun onBindViewHolder(holder: MvHolder, position: Int) {
        //如果是 最后一条就不需要设置参数了
        if(position==list.size){
            return
        }
        //获取数据
        val data = list.get(position)
        //获取布局                               转换类型
        val itemView = holder.itemView as MvItemView

        //条目刷新
        itemView.setData(data)

        //点击事件
        itemView.setOnClickListener{
            context.startActivity(Intent(context, TextTureVideoPlayerActivity::class.java))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MvHolder {
        if(viewType==1){
            return MvHolder(LoadMoreView(parent.context))
        }else{
            //普通条目
            return MvHolder(MvItemView(parent.context))
        }

    }

    class MvHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    interface OnClikListener{
        fun onClick()
    }
}