package wipro.wiprotest.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import wipro.wiprotest.model.Data
import wipro.wiprotest.utility.RetrofitHelper
import java.io.File


class LandingViewModel : ViewModel() {

    var cacheDir: File?=null

    fun setCacheDirectory(cacheDir:File){
        this.cacheDir= cacheDir
    }

    private var userList: MutableLiveData<Data>? = null

    fun getMutableObject(): MutableLiveData<Data>? {

        if (userList == null) {
            userList = loadDataFromUrl()
        }
        return userList
    }

    fun refreshData(){
        userList=null

    }

    private fun loadDataFromUrl(): MutableLiveData<Data>? {

        val mutable :MutableLiveData<Data>? = MutableLiveData()
        val retrofitHelper= RetrofitHelper(cacheDir)
        retrofitHelper.makeNetworkCall(mutable)
        return mutable
    }


}


