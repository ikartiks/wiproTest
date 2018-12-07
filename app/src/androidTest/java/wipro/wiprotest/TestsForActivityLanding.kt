package wipro.wiprotest


import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.kartik.grevocab.adapters.LandingRecyclerAdapter
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import wipro.wiprotest.model.Data


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestsForActivityLanding {

    //internal class ApplicationStub : Application()

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
        
        val view = activityLanding?.findViewById<RecyclerView>(R.id.recyclerView)
        assertNotNull(view)
    }

    //this test failed initially, however i added code to handle receiving an empty object from server.
    // so as of now both tests are of success scenarios
    @Test
    fun testResponseFromServer(){

        val data=Data()
        activityLanding?.runOnUiThread {
            activityLanding?.onDataChange(data)
        }
        val view = activityLanding?.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = view?.adapter as LandingRecyclerAdapter
        assertNotNull(adapter)
    }
}
