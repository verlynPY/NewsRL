package com.example.newsrealtime.model.Repository

import com.example.newsrealtime.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun GetAllNews(@Query("q") Search: String, @Query("apiKey") ApiKey: String): Example

}