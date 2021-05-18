package com.example.newsrealtime.model.Repository.Room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getAllHistory(): List<History>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg history: History)

    @Delete
    suspend fun delete(history: History)
}