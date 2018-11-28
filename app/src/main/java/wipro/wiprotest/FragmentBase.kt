package wipro.wiprotest

import android.app.AlertDialog
import android.support.v4.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.IBinder
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import wipro.wiprotest.R
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
abstract class FragmentBase : Fragment() {


    fun isConnected() : Boolean{

        val connectivityManager = this.activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo?.isConnected ?: false
    }

    fun isTablet(): Boolean{


        val xlarge = this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == 4
        val large = this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK == Configuration.SCREENLAYOUT_SIZE_LARGE
        return xlarge || large
    }

    //PS somehow getting resources as instance variable is creating memory issues,and app to crash
    //AlertDialog ad;


    //    public void showLoader(){
    //        GifImageView gif=(GifImageView) findViewById(R.id.Loading);
    //        gif.setVisibility(View.VISIBLE);
    //    }
    //    public void hideLoader(){
    //        GifImageView gif=(GifImageView) findViewById(R.id.Loading);
    //        gif.setVisibility(View.GONE);
    //    }

    protected fun log(message: String) {
        Log.e(this.javaClass.simpleName, "" + message)
    }

    fun hideSoftInput(binder: IBinder) {
        //myEditText.getWindowToken()
        val mgrs = this.activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgrs.hideSoftInputFromWindow(binder, InputMethodManager.SHOW_FORCED)
    }

    fun showSoftInput(view: View) {
        val mgrs = this.activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mgrs.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun showCustomMessage(message: String): AlertDialog? {

        val adb = AlertDialog.Builder(this.activity)
        adb.setTitle(this.resources.getString(R.string.app_name))
        adb.setMessage(message)
        adb.setPositiveButton("OK") { dialog, which -> dialog.dismiss() }
        adb.create().show()
        return null
    }

    fun showToast(message: String) {
        val toast = Toast.makeText(this.activity, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun resolveActivity(intent: Intent): Boolean {
        val packages: List<ApplicationInfo>
        val pm: PackageManager
        pm = this.activity!!.packageManager
        val info = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return if (info != null) true else false
    }

    fun isPackagePresent(targetPackage: String): Boolean {
        val packages: List<ApplicationInfo>
        val pm: PackageManager
        pm = this.activity!!.packageManager
        packages = pm.getInstalledApplications(PackageManager.PERMISSION_GRANTED)
        for (packageInfo in packages) {
            if (packageInfo.packageName == targetPackage)
                return true
        }
        return false
    }

    fun resolveAttribute(attr: Int): Int {
        val typedvalueattr = TypedValue()
        this.activity!!.theme.resolveAttribute(attr, typedvalueattr, true)
        return typedvalueattr.resourceId
    }

    fun resolveAttributeColor(attr: Int): Int {
        val typedvalueattr = TypedValue()
        this.activity!!.theme.resolveAttribute(attr, typedvalueattr, true)
        return typedvalueattr.data
    }


    fun showEndPop() {

        val adb = AlertDialog.Builder(this.activity)
        adb.setTitle(this.resources.getString(R.string.app_name))
        adb.setMessage("Are you sure you want to quit?")
        adb.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            activity!!.finish()
        }
        adb.setNegativeButton("No") { dialog, which -> dialog.dismiss() }
        adb.create().show()
    }

    fun putBoolean(key: String, value: Boolean) {

        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun putLong(key: String, value: Long) {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        return sharedPreferences.getLong(key, defaultValue)
    }

    fun putString(key: String, value: String) {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun putArray(key: String, value: ArrayList<String>) {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value.size)

        for (i in value.indices) {
            editor.putString(key + i, value[i])
        }

        editor.commit()
    }

    fun getArray(key: String): ArrayList<String> {

        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val value = ArrayList<String>()
        val size = sharedPreferences.getInt(key, 0)

        for (i in 0 until size) {
            value.add(sharedPreferences.getString(key + i, ""))
        }
        return value
    }

    fun getString(key: String, defaultValue: String): String? {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue)

    }

    fun remove(key: String) {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.commit()
    }

    operator fun contains(key: String): Boolean {

        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.activity!!.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        return sharedPreferences.contains(key)
    }


}// Required empty public constructor
