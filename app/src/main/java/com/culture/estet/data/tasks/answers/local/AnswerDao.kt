package com.culture.estet.data.tasks.answers.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AnswerDao {

    @Query("SELECT * FROM answers")
    suspend fun getAll(): List<AnswerEntity>

    @Upsert
    suspend fun upsertAllAnswers(answers: List<AnswerEntity>)

    @Query("SELECT * FROM answers WHERE question_id = :questionId")
    suspend fun getAllByQuestionId(questionId: String): List<AnswerEntity>
}