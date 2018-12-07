package wipro.wiprotest.utility

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wipro.wiprotest.model.Data
import java.io.File

class RetrofitHelper(val cacheDir: File?) {


    fun getRetrofitService(): RetrofitInterface {

        val cacheSize = (10 * 1024 * 1024).toLong()

        val client = OkHttpClient.Builder()
                //uses max-age parameter of server
            .cache(Cache(cacheDir!!, cacheSize))
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.baseUrl)
            .client(client)

            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitInterface::class.java)
        return service;
    }

    fun makeNetworkCall(mutable: MutableLiveData<Data>?) {

        val service = getRetrofitService()
        val result = service.getData()
        //result.request().cacheControl().immutable()
        result.enqueue(object : Callback<Data> {

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.e("comes failure", " " + t.message)
                mutable?.value = null
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {

                val data = response?.body()
                mutable?.value = data
            }
        })
    }
}