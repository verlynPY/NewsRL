package com.example.newsrealtime.model.Repository

import android.content.ContentValues.TAG
import android.util.Log
import kotlinx.coroutines.flow.flow

class DataFlow {

    val data: Data = Data()
    fun SettingData(Search: String) = flow{
        var ListArticle = data.GetNews(Search)
        Log.e(TAG, "$ListArticle")
        for(i in ListArticle){
            emit(i)
        }

    }

}