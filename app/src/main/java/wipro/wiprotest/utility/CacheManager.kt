package wipro.wiprotest.utility

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import wipro.wiprotest.R
import wipro.wiprotest.model.Data

class CacheManager(val context: Context){

    var data : Data?=null
    lateinit var fileName:String
    lateinit var handler: Handler

    constructor(  handler: Handler, context: Context) : this(context){
        this.handler= handler
    }

    constructor(context: Context, data : Data?,  fileName:String):this(context){
        this.data = data
        this.fileName = fileName
    }

    fun saveCache(){
        Thread(){
            data?.let { saveObject(context, data!!, context.getString(R.string.fileName)) }
        }.start()
    }

    fun retriveCache(){
        Thread(){
            val data = retriveObject(context,context.getString(R.string.fileName))
            //Thread.sleep(5000)
            data?.let {
                val message = Message()
                val bundle = Bundle()
                bundle.putParcelable("data", it)
                message.setData(bundle)
                message.what= 1
                handler.sendMessage(message)
            }
        }.start()
    }
}