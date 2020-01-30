package com.example.cermati.View

import com.example.cermati.Network.Model.ItemsItem

interface View {
    fun showProgress(show: Boolean)
    fun onError(error: String)
    fun showUsers(result: List<ItemsItem>)
}