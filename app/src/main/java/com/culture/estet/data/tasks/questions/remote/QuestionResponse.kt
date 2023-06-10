package com.culture.estet.data.tasks.questions.remote

import com.culture.estet.data.tasks.questions.local.QuestionEntity
import java.io.Serializable

data class QuestionResponse(
    val id: String,
    val question: String,
    val level: Int,
    val artType: Int,
    val score: Int,
    val description: String,
): Serializable



fun QuestionResponse.toQuestionEntity(): QuestionEntity {
    return QuestionEntity(
        id = id,
        question = question,
        level = level,
        artType = artType,
        score = score,
        description = description
    )
}

fun List<QuestionResponse>.toQuestionListEntity() = map(QuestionResponse::toQuestionEntity)
