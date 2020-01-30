package com.example.cermati.Network.Model

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("total_count")
    val totalCount: Int? = null,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean? = null,

    @field:SerializedName("items")
    var items: List<ItemsItem>? = null,


    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("documentation_url")
    val documentationUrl: String? = null
)