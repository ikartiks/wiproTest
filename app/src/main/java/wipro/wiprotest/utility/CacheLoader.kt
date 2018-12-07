package wipro.wiprotest.utility

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import wipro.wiprotest.R



class CacheLoader ( val handler: Handler, val  context:Context) /*private constructor()*/  :Runnable{

//    lateinit var handler:Handler
//    lateinit var context:Context

//    constructor(  handler: Handler,  context:Context): this(){
//        this.handler=handler
//        this.context= context
//
//    }

    override fun run() {


        //val msg = handler.

        val data = retriveObject(context,context.getString(R.string.fileName))
        //Thread.sleep(5000)
        data?.let {
            val message =Message()
            val bundle = Bundle()
            bundle.putParcelable("data", it)
            message.setData(bundle)
            message.what= 1
            handler.sendMessage(message)
        }


    }
}