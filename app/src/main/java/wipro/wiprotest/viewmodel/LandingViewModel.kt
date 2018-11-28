package wipro.wiprotest.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import wipro.wiprotest.model.Data
import wipro.wiprotest.utility.RetrofitHelper


class LandingViewModel : ViewModel() {

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
        val retrofitHelper= RetrofitHelper()
        retrofitHelper.makeNetworkCall(mutable)
        return mutable
    }


}


