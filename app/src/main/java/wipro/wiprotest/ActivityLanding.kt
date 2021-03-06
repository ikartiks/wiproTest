package wipro.wiprotest


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kartik.grevocab.adapters.LandingRecyclerAdapter
import kotlinx.android.synthetic.main.activity_landing.*
import wipro.wiprotest.model.Data
import wipro.wiprotest.utility.CacheManager
import wipro.wiprotest.viewmodel.LandingViewModel

// note the use of base activity class for common functionality
class ActivityLanding : BaseActivity(), LandingRecyclerAdapter.OnItemClickListener {

    lateinit var viewModel: LandingViewModel
    lateinit var context: Context
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        setSupportActionBar(toolbar)
        handler = object : Handler() {
            override fun handleMessage(message: Message) {
                // code here
                if (message.what == 1) {
                    val bundle = message.data as Bundle
                    val data = bundle.getParcelable<Data>("data")
                    data?.let { onDataChange(it) }
                }
            }
        }

        viewModel = ViewModelProviders.of(this).get(LandingViewModel::class.java)
        viewModel.setCacheDirectory(this.cacheDir)

        context = this
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        //not loading cache on main thread, and hanging the ui
        CacheManager(handler, this).retriveCache()
        swipeRefreshLayout.setOnRefreshListener {
            // cancel the Visual indication of a refresh
            swipeRefreshLayout.isRefreshing = false
            refresh()
        }

        if (!isConnected()) {
            showCustomMessage(getString(R.string.noInternet))
            return
        }

        viewModel.getMutableObject()?.observe(this, object : Observer<Data> {
            override fun onChanged(data: Data?) {

                CacheManager(context, data, "data").saveCache()
                onDataChange(data)
            }
        })
    }


    fun refresh() {

        if (!isConnected()) {
            showCustomMessage(getString(R.string.noInternet))
            return
        }
        viewModel.refreshData()
        val adapter = recyclerView.adapter as LandingRecyclerAdapter?
        adapter?.clearItems()
        adapter?.notifyDataSetChanged()
        viewModel.getMutableObject()?.observe(this, object : Observer<Data> {
            override fun onChanged(data: Data?) {
                onDataChange(data)
            }
        })
    }


    fun onDataChange(data: Data?) {
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        val adapter = LandingRecyclerAdapter(data?.rows, this)
        recyclerView.adapter = adapter

        adapter.setOnItemClickLickListener(this)
        data?.title?.let { supportActionBar?.title = it }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.refresh) {
            // up navigation in our case
            refresh()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(view: View, position: Int) {
        view.tag?.let { showCustomMessage((view.tag as String) + " clicked") }
    }

}
