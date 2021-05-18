package com.example.newsrealtime.model.Repository.Room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsrealtime.model.Repository.Room.CredentialDataBase.SEARCH

@Entity(tableName = "history")
 data class History (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "Search")
    val Search: String?
)