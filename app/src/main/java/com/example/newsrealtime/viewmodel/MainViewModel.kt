package com.example.newsrealtime.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsrealtime.Article
import com.example.newsrealtime.Example
import com.example.newsrealtime.model.Repository.Data
import com.example.newsrealtime.model.Repository.DataFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val dataflow = DataFlow()
    var ListArticle = ArrayList<Article>()

     fun GetArticles(){
         viewModelScope.launch(Dispatchers.Main) {
             var flows = dataflow.SettingData("Apple")
                     .flowOn(Dispatchers.IO)
                     .transform {
                         emit(it)
                     }
                     .collect {
                         Log.e(TAG, "$it")
                     }
         }
    }
}