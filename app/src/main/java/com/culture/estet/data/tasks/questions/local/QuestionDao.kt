package com.culture.estet.data.tasks.questions.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface QuestionDao {

    @Query("SELECT * FROM questions")
    suspend fun getAll(): List<QuestionEntity>

    @Upsert
    suspend fun upsertAllQuestions(questions: List<QuestionEntity>)

    @Query("SELECT * FROM questions WHERE art_type = :artType")
    suspend fun getAllByArtType(artType: Int): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE art_type = :artType AND level = :level")
    suspend fun getAllByParameters(artType: Int, level: Int): List<QuestionEntity>


}