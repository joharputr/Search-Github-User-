package com.example.cermati

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cermati.Adapter.UserSearchAdapter
import com.example.cermati.Network.Model.ItemsItem
import com.example.cermati.Presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), com.example.cermati.View.View {
    private val list = mutableListOf<ItemsItem>()
    val searchAdapter = UserSearchAdapter(list)
    lateinit var queryString: String
    var page = 0

    var presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarC)
        page = 1

        search_home.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                queryString = query.toString()
                Log.d("TestQuery", "QueryTextSubmit = " + queryString)
                list.clear()
                page = 1
                getPage()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                queryString = query.toString()
                return false
            }

        })
        setupRecyclerView()

        recyclerViewMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val countItem = linearLayoutManager.itemCount
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                Log.d("checkData", "countItem: $countItem")
                Log.d("checkData", "lastVisiblePosition: $lastVisiblePosition")
                Log.d("checkData", "isLastPosition: $isLastPosition")
                if (isLastPosition) {
                    page++
                    Log.d("cekPage = ", page.toString())
                    getPage()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        recyclerViewMain.run {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    fun getPage() {
        presenter.getUsers(queryString, page)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progress_bar.visibility = View.VISIBLE
        } else
            progress_bar.visibility = View.GONE
    }

    override fun onError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showUsers(result: List<ItemsItem>) {
        list.addAll(result)
        searchAdapter.notifyDataSetChanged()
    }
}
