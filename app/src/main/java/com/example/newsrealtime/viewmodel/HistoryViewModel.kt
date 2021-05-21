package com.example.newsrealtime.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.newsrealtime.Article
import com.example.newsrealtime.model.Repository.Room.AppDataBase
import com.example.newsrealtime.model.Repository.Room.History
import com.example.newsrealtime.model.Repository.Room.HistoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {

    val ListSearch = MutableLiveData<List<History>>()
    private val _uiState = MutableStateFlow(HistoryViewModel.HistoryNewsUiState.Success(null))
    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<HistoryViewModel.HistoryNewsUiState> = _uiState

    fun SettingDataBase(context: Context){
        var db = AppDataBase(context)
        GlobalScope.launch {
            db.historyDao().insert(History(2,"Apple"))

        }
    }

    fun GetHistory(context: Context){
        var db = AppDataBase(context)
        var list: List<History> = listOf()
        viewModelScope.launch(Dispatchers.Main) {
            GetSearch(context)
                    .flowOn(Dispatchers.IO)
                    .transform {
                        emit(it)
                    }
                    .collect { item ->
                        _uiState.value = HistoryNewsUiState.Success(item)
                        println(item)
                    }

        }
    }

    fun GetSearch(context: Context) = flow{
        var db = AppDataBase(context)
        var data = db.historyDao().getAllHistory()
        emit(data)
    }

    sealed class HistoryNewsUiState {
        data class Success(val history: List<History>?): HistoryNewsUiState()
        data class Error(val exception: Throwable): HistoryNewsUiState()
    }
}
