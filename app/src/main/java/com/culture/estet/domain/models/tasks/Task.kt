package com.culture.estet.domain.models.tasks
data class Task(
    val id: String,
    val type: TasksArtType,
    val level: TasksLevelType,
    val progress: Float,
    val questionsCount: Int,
    val answerCount: Int
)
