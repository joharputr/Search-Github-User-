package com.example.cermati

import android.app.Application
import com.example.cermati.Network.ApiRequest
import com.example.cermati.Network.NetworkConfig

class App : Application() {

    companion object {

        lateinit var apiRequest: ApiRequest
    }

    override fun onCreate() {
        super.onCreate()
        apiRequest = NetworkConfig.getRetrofit().create(ApiRequest::class.java) as ApiRequest
    }

}