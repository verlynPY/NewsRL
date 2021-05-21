package com.example.newsrealtime.model.Repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Credentials {

    val URL = "https://newsapi.org/v2/"
    val ApiKey = "0a74832aaaf84421b9d72b9acd5331f5"

    val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .run {
                create(ApiService::class.java)
            }

}