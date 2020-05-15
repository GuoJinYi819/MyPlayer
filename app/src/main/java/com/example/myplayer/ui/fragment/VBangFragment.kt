package com.example.myplayer.ui.fragment

import android.content.AsyncQueryHandler
import android.content.ContentResolver
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
import com.example.myplayer.base.BaseFragment
import com.example.myplayer.util.CursorUtil

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
                //查询完成回调
                CursorUtil.logCursor(cursor!!)
            }
        }
        //开始查询
        handler.startQuery(0,null,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
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
}