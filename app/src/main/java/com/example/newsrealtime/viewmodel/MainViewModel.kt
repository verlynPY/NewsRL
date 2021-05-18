package com.example.newsrealtime.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsrealtime.Article
import com.example.newsrealtime.Example
import com.example.newsrealtime.model.Repository.Data
import com.example.newsrealtime.model.Repository.DataFlow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val dataflow = DataFlow()


    private val _uiState = MutableStateFlow(LatestNewsUiState.Success(null))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LatestNewsUiState> = _uiState
    var ListArticles = MutableLiveData<ArrayList<Article>>()
     //fun GetArticles(){
    init {
         viewModelScope.launch(Dispatchers.Main) {
             var flows = SettingData("Tesla")
                     .flowOn(Dispatchers.IO)
                     .transform {
                         emit(it)
                     }
                     .collect { example ->
                         _uiState.value = LatestNewsUiState.Success(example.articles)
                         //Log.e(TAG, "${example.articles}")
                     }
         }
     }

    //}

    val data: Data = Data()
    fun SettingData(Search: String) = flow{
        var ListArticle = data.GetNews(Search)
        //for(i in ListArticle){
            emit(ListArticle)
            //Log.e(TAG, "$i")
        //}

    }

    sealed class LatestNewsUiState {
        data class Success(val news: ArrayList<Article>?): LatestNewsUiState()
        data class Error(val exception: Throwable): LatestNewsUiState()
    }

}