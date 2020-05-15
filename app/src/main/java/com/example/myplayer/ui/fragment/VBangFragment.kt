package com.example.myplayer.ui.fragment

import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.myplayer.R
import com.example.myplayer.adapter.VbangAdapter
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.bean.AudioBean
import com.example.myplayer.ui.activity.AudioPlayerActivity
import com.example.myplayer.util.CursorUtil
import kotlinx.android.synthetic.main.fragment_vbang.*

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 8:43
 * @Description: 用途：完成特定功能
 */
class VBangFragment:BaseFragment() {
    val handler = object :Handler(){
        override fun handleMessage(msg: Message) {
            msg?.let {
                val currsor:Cursor = msg.obj as Cursor;
                CursorUtil.logCursor(currsor)
            }
        }
    }
    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_vbang,null)

    }
    var adapter:VbangAdapter? = null
    override fun initListener() {

        adapter = VbangAdapter(context,null)
        listView.adapter = adapter
        
        //条目点击事件
        listView.setOnItemClickListener { parent, view, position, id ->
            //获取数据集合
            val cursor = adapter?.getItem(position) as Cursor
            //通过当前位置cursor获取整个播放列表
            val list:ArrayList<AudioBean> = AudioBean.getAudioBeans(cursor)

            //跳转到音乐播放界面
           startActivity(Intent(context,AudioPlayerActivity::class.java).putExtra("list",list).putExtra("position",position))
        }
    }

    override fun initData() {
        //获取音乐数据
        val resolver = context?.contentResolver
//        val cursor = resolver?.query(
//            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
//                MediaStore.Audio.Media.DATA
//                , MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.DISPLAY_NAME
//                , MediaStore.Audio.Media.ARTIST
//            ), null, null, null
//        )
        //打印所有的数据
       // CursorUtil.logCursor(cursor!!)
        //开启线程
//        Thread(object :Runnable{
//            override fun run() {
//                val cursor = resolver?.query(
//                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
//                        MediaStore.Audio.Media.DATA
//                        , MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.DISPLAY_NAME
//                        , MediaStore.Audio.Media.ARTIST
//                    ), null, null, null
//                )
//                val msg = Message.obtain()
//                msg.obj = cursor
//                handler.sendMessage(msg)
//            }
//        }).start()

        //asynctask
       // AudioTask().execute(resolver)
        val  handler = object :AsyncQueryHandler(resolver){
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                //查询完成回调回来 打印
               // CursorUtil.logCursor(cursor!!)
                //刷新列表
                //1 先设置数据源
                //2 刷新adapter
                (cookie as VbangAdapter).swapCursor(cursor)
            }
        }
        //开始查询
        handler.startQuery(0,adapter,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DATA
                        , MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.DISPLAY_NAME
                        , MediaStore.Audio.Media.ARTIST
                    ), null, null, null)
    }


    //音乐查询 异步任务 asynctask
    class AudioTask:AsyncTask<ContentResolver,Void,Cursor>(){
        //在新线程中执行任务
        override fun doInBackground(vararg params: ContentResolver?): Cursor {
            val cursor = params[0]?.query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
                        MediaStore.Audio.Media.DATA
                        , MediaStore.Audio.Media.SIZE, MediaStore.Audio.Media.DISPLAY_NAME
                        , MediaStore.Audio.Media.ARTIST
                    ), null, null, null
                )
            return cursor!!
        }

        //将任务结果回调到 当前方法
        override fun onPostExecute(result: Cursor?) {
            CursorUtil.logCursor(result!!)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        //关闭 cursor
        // 1 获取 adapter cursor
        // 将 adapter中的 cursor设置为null
        adapter?.changeCursor(null)
    }

}