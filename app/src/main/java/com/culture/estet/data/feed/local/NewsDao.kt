package com.culture.estet.data.feed.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_entities_table")
    fun observeAll(): Flow<List<NewsEntity>>

    @Upsert
    suspend fun upsertAllNews(task: List<NewsEntity>)

    @Query("DELETE FROM news_entities_table")
    suspend fun deleteAll()
}