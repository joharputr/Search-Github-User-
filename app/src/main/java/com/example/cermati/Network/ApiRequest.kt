package com.example.cermati.Network

import com.example.cermati.Network.Model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("search/users")
    fun search_users(
        @Query(
            "q"
        ) origin: String?, @Query(
            "page"
        ) page: Int?
    ): Call<Response>

}