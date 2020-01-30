package com.example.cermati.Presenter

import com.example.cermati.App
import com.example.cermati.Network.Model.Response
import com.example.cermati.View.View
import retrofit2.Call
import retrofit2.Callback

class Presenter(private val view: View) {

    private val apiService by lazy {
        App.apiRequest
    }

    fun getUsers(query: String, page: Int) {
        view.showProgress(true)
        apiService.search_users(query, page).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                view.onError(t.localizedMessage)
                view.showProgress(false)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                view.showProgress(false)
                val body: Response? = response.body()

                if (body != null) {
                    body.items.let {result->
                        view.showUsers(result!!)
                    }
                } else {
                    view.onError(response.message())
                }
            }
        })
    }



}