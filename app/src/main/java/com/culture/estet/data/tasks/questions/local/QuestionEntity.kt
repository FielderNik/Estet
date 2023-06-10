package com.culture.estet.data.tasks.questions.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.culture.estet.data.tasks.questions.local.QuestionEntity.Companion.QUESTIONS_TABLE_NAME


@Entity(tableName = QUESTIONS_TABLE_NAME)
data class QuestionEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "question")
    val question: String,

    @ColumnInfo(name = "level")
    val level: Int,

    @ColumnInfo(name = "art_type")
    val artType: Int,

    @ColumnInfo(name = "score")
    val score: Int,

    @ColumnInfo(name = "description")
    val description: String,

) {
    companion object {
        const val QUESTIONS_TABLE_NAME = "questions"
    }
}