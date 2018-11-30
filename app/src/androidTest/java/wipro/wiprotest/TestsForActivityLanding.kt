package wipro.wiprotest

import android.app.Application
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.util.Log
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import wipro.wiprotest.utility.retriveObjectFailure


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestsForActivityLanding {


    internal class ApplicationStub : Application()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("wipro.wiprotest", appContext.packageName)
    }

    @Rule
    @JvmField
    val testRule = ActivityTestRule(ActivityLanding::class.java)

    var  activityLanding : ActivityLanding? = null

    @Before
    fun setup(){
        InstrumentationRegistry.getContext()
        activityLanding = testRule.activity
    }

    @After
    fun tearDown(){
        activityLanding=null
    }

    @Test
    fun successfulAppLaunch() {

        // not using data binding here for obvious reasons.
        val view = activityLanding?.findViewById<RecyclerView>(R.id.recyclerView)
    }

    @Test
    fun failureScenario() {

        val data = retriveObjectFailure(InstrumentationRegistry.getContext(),"data")
        data?.let { Log.e("app launch successfully","nice") }
    }
}
