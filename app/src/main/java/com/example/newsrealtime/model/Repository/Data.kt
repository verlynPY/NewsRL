package com.example.newsrealtime.model.Repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsrealtime.Article
import com.example.newsrealtime.Example
import com.example.newsrealtime.model.Repository.Credentials.retrofit
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Data {

    suspend fun GetNews(Search: String): Example{
        var liveData = ArrayList<Article>()

        return retrofit.GetAllNews(Search, Credentials.ApiKey)

    }

    suspend fun GetTopNews(Country: String): Example{
        var liveData = ArrayList<Article>()

        return retrofit.GetTopNews(Country, Credentials.ApiKey)

    }

}