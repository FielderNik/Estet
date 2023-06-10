package com.culture.estet.data.tasks.statistics.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface StatisticsDao {

    @Query("SELECT * FROM statistics WHERE user_id = :userId")
    suspend fun getAllByUserId(userId: String): List<StatisticsEntity>

    @Upsert
    suspend fun upsertAllStatistics(statistics: List<StatisticsEntity>)
}