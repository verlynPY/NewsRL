package com.example.newsrealtime.model.Repository.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.internal.Internal.instance

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
                @Volatile
                private var INSTANCE: AppDataBase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDataBase::class.java, "todo-list.db")
            .build()
            }

        }
