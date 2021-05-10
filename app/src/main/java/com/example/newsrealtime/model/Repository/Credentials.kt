package com.example.newsrealtime.model.Repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Credentials {

    val URL = "https://newsapi.org/v2/"
    val ApiKey = "bab374c174f7476cb338de2f76011036"

    val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .run {
                create(ApiService::class.java)
            }

}