package com.culture.estet.data.tasks.statistics.remote

import com.culture.estet.data.tasks.statistics.local.StatisticsEntity
import com.google.gson.annotations.SerializedName

data class StatisticsResponse(
    val id: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("question_id")
    val questionId: String,
    @SerializedName("selected_answer_id")
    val selectedAnswerId: String,
)


fun StatisticsResponse.toStatisticsEntity(): StatisticsEntity {
    return StatisticsEntity(
        id = id,
        userId = userId,
        questionId = questionId,
        selectedAnswerId = selectedAnswerId
    )
}

fun List<StatisticsResponse>.toStatisticsListEntity() = map(StatisticsResponse::toStatisticsEntity)