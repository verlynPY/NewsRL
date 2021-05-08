package com.example.newsrealtime

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Example {

    @SerializedName("articles")
    @Expose
    var articles: ArrayList<Article>? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: Long? = null
}