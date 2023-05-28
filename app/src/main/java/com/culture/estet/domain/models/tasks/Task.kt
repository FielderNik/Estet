package com.culture.estet.domain.models.tasks
data class Task(
    val id: String,
    val type: TaskArtType,
    val level: TaskLevelType,
    val questionsCount: Int,
    val answerCount: Int
)
