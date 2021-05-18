package com.example.newsrealtime.model.Repository.Room

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
/*
class HistoryApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val dataBase by lazy { AppDataBase.getDataBase(this, applicationScope) }
    val repository by lazy { HistoryRepository(dataBase.historyDao()) }
}*/