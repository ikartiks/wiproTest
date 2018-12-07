package wipro.wiprotest

import android.app.AlertDialog
import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){


    fun isConnected(): Boolean {

        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo?.isConnected ?: false
    }

    fun showCustomMessage(message: String) {

        val adb = AlertDialog.Builder(this)
        adb.setTitle(this.resources.getString(R.string.app_name))
        adb.setMessage(message)
        adb.setPositiveButton("OK",{ dialog, which -> dialog.dismiss() })
        adb.create().show()
    }
}