package com.culture.estet.domain.models

data class Question(
    val id: String,
    val question: String,
    val type: QuestionType,
    val answers: List<Answer>
)
