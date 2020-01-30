package com.example.cermati.Network.Model

import com.google.gson.annotations.SerializedName

data class ItemsItem(


    @field:SerializedName("login")
    val login: String? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = null

)