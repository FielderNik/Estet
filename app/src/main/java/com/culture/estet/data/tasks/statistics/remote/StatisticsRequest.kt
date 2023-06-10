package com.culture.estet.data.tasks.statistics.remote

import com.google.gson.annotations.SerializedName

data class StatisticsRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("question_id")
    val questionId: String,
    @SerializedName("selected_answer_id")
    val selectedAnswerId: String
)
