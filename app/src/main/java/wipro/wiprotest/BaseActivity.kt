package wipro.wiprotest

import android.app.AlertDialog
import android.content.Context
import android.graphics.Point
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){


    var screenWidth: Int = 0
    var screenHeight:Int = 0
    var statusBarHeight:Int = 0
    var navigationBarHeight:Int = 0


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


        val resources = this.resources
        var resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        } else {
            statusBarHeight = 0
        }
        resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId)
        } else {
            navigationBarHeight = 0
        }

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y

    }

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