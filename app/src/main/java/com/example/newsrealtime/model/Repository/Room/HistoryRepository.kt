package com.example.newsrealtime.model.Repository.Room

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
/*
class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistory: Flow<List<History>> = historyDao.getAllHistory()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(history: History){
        historyDao.insert(history)
    }
}*/