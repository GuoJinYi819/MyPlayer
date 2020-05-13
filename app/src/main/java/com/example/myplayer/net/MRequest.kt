package com.example.myplayer.net

import com.example.myplayer.bean.HomeBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.ParameterizedType

/**ClassName: MyPlayer
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/13 0013 14:12
 * @Description: 用途：所有请求基类
 */
open class MRequest<RESPONSE>(val url:String,val handler:ResponseHandler<RESPONSE>) {
    fun parseResult(result: String?): RESPONSE {
        //解析
        val gson = Gson()
        //获取泛型类型
        val type= (this.javaClass.genericSuperclass
                as ParameterizedType).getActualTypeArguments()[0]
        var json = gson.fromJson<RESPONSE>(result,type)
        return json
    }


}