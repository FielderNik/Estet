package com.culture.estet.domain.models.questions

data class Question(
    val id: String,
    val ordinal: Int,
    val question: String,
    val type: QuestionType,
    val answers: List<Answer>,
    val score: Int,
    val description: String,
) {
    companion object {
        fun getEmptyQuestion(): Question {
            return Question(
                "", 0,"", QuestionType.EASY, emptyList(), 0, ""
            )
        }
    }
}
