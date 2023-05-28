package com.culture.estet.data.map.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolsDao {
    @Query("SELECT * FROM schools_entities_table")
    fun observeAll(): Flow<List<SchoolEntity>>

    @Upsert
    suspend fun upsertAllSchools(task: List<SchoolEntity>)

    @Query("DELETE FROM schools_entities_table")
    suspend fun deleteAll()
}