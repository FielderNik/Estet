package com.culture.estet.data.tasks.answers.remote

import com.culture.estet.data.tasks.answers.local.AnswerEntity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnswerResponse(
    val id: String,
    @SerializedName("question_id")
    val questionId: String,
    val answer: String,
    @SerializedName("is_correct")
    val isCorrect: Boolean,
): Serializable


fun AnswerResponse.toAnswerEntity(): AnswerEntity {
    return AnswerEntity(
        id = id,
        questionId = questionId,
        answer = answer,
        isCorrect = isCorrect
    )
}

fun List<AnswerResponse>.toAnswerListEntity() = map(AnswerResponse::toAnswerEntity)
