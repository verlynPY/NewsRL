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
import com.example.newsrealtime.model.Repository.Room.History

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val dataflow = DataFlow()

    private val _Country = MutableLiveData("")

    val Country: LiveData<String> = _Country

    private val _uiState = MutableStateFlow(LatestNewsUiState.Success(null))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    fun onCountryChange(newCountry: String) {
        _Country.value = newCountry
    }

    /*init {
         viewModelScope.launch(Dispatchers.Main) {
             var flows = GettingData("US")
                     .flowOn(Dispatchers.IO)
                     .transform {
                         emit(it)
                     }
                     .collect { item ->
                         _uiState.value = LatestNewsUiState.Success(item.articles)
                         //Log.e(TAG, "${example.articles}")
                     }
         }
     }*/

    fun EmitTopNews(Country: String){
        viewModelScope.launch(Dispatchers.Main) {
            var flows = GettingData(Country)
                    .flowOn(Dispatchers.IO)
                    .transform {
                        emit(it)
                    }
                    .collect { item ->
                        _uiState.value = LatestNewsUiState.Success(item.articles)
                        //Log.e(TAG, "${example.articles}")
                    }
        }
    }

    fun EmitDataSearch(Search: String){

        viewModelScope.launch(Dispatchers.Main){
            GettingDataSearch(Search)
                    .flowOn(Dispatchers.IO)
                    .transform {
                        emit(it)
                    }
                    .collect { item ->
                        
                        _uiState.value = LatestNewsUiState.Success(item.articles)
                    }
        }
    }

    val data: Data = Data()
    fun GettingData(Country: String) = flow{
        try {
            var ListArticle = data.GetTopNews(Country)
            emit(ListArticle)
        }
        catch(e: Exception){
            println(e)
        }
    }

    fun GettingDataSearch(Search: String) = flow{
        try {
            var ListArticlesearch = data.GetNews(Search)
            emit(ListArticlesearch)
        }
        catch(e: Exception){
            println(e)
        }
    }

    sealed class LatestNewsUiState {
        data class Success(val news: ArrayList<Article>?): LatestNewsUiState()
        data class Error(val exception: Throwable): LatestNewsUiState()
    }

}