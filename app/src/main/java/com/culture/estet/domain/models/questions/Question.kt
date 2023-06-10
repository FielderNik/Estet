package com.culture.estet.domain.models.questions

import com.culture.estet.domain.models.tasks.TaskLevelType

data class Question(
    val id: String,
    val ordinal: Int,
    val question: String,
    val type: TaskLevelType,
    val answers: List<Answer>,
    val score: Int,
    val description: String,
) {
    companion object {
        fun getEmptyQuestion(): Question {
            return Question(
                "", 0,"", TaskLevelType.BEGINNER, emptyList(), 0, ""
            )
        }
    }
}
