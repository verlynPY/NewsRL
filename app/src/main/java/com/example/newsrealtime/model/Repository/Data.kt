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

        /*val client: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        client.GetAllNews(Search, Credentials.ApiKey).enqueue(object: Callback<Example>{
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                if(response.isSuccessful){
                    var articles = response.body()!!.articles
                    for(i in articles!!){
                        liveData.add(i)
                        //Log.e(TAG, "$i")
                    }
                    //Log.e(TAG, "${call.request().url()}")

                }
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                t.printStackTrace()
            }

        })*/

        //return liveData

    }

}