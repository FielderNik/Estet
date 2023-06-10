package com.culture.estet.data.tasks.answers.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.culture.estet.data.tasks.answers.local.AnswerEntity.Companion.ANSWERS_TABLE_NAME


@Entity(tableName = ANSWERS_TABLE_NAME)
data class AnswerEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "question_id")
    val questionId: String,

    @ColumnInfo(name = "answer")
    val answer: String,

    @ColumnInfo(name = "is_correct")
    val isCorrect: Boolean,
) {
    companion object {
        const val ANSWERS_TABLE_NAME = "answers"
    }
}