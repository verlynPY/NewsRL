package com.example.newsrealtime

import com.google.gson.annotations.SerializedName



data class Source(
    @SerializedName("id")
    var id: Any? = null,

    @SerializedName("name")
    var name: String? = null
)