package com.culture.estet.data.tasks.statistics.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.culture.estet.data.tasks.statistics.local.StatisticsEntity.Companion.STATISTICS_TABLE_NAME


@Entity(tableName = STATISTICS_TABLE_NAME)
data class StatisticsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "user_id")
    val userId: String,

    @ColumnInfo(name = "question_id")
    val questionId: String,

    @ColumnInfo(name = "selected_answer_id")
    val selectedAnswerId: String,

) {
    companion object {
        const val STATISTICS_TABLE_NAME = "statistics"
    }
}