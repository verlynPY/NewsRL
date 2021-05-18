package com.example.newsrealtime.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.newsrealtime.model.Repository.Room.AppDataBase
import com.example.newsrealtime.model.Repository.Room.History
import com.example.newsrealtime.model.Repository.Room.HistoryDao
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch

class HistoryViewModel(): ViewModel() {

    fun SettingDataBase(context: Context){
        val db = AppDataBase(context)
        GlobalScope.launch {
            db.historyDao().insert(History(1,"Tesla"))
            var data = db.historyDao().getAllHistory()

            data?.forEach {
                println(it)
            }
        }
    }

}