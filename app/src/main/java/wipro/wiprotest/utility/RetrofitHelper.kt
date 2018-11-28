package wipro.wiprotest.utility

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import wipro.wiprotest.model.Data

class RetrofitHelper{


    fun getRetrofitService():RetrofitInterface{
        val retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitInterface::class.java)
        return service;
    }

    fun makeNetworkCall(mutable:MutableLiveData<Data>?){

        val service = getRetrofitService()
        val result = service.getData()
        result.enqueue(object : Callback<Data> {

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.e("comes failure"," "+ t.message)
                mutable?.value=null
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {

                val data =response?.body()
                mutable?.value=data
            }
        })
    }
}