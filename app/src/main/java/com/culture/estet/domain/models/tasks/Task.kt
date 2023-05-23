package com.culture.estet.domain.models.tasks

import java.util.UUID

data class Task(
    val id: String,
    val type: TasksArtType,
    val level: TasksLevelType,
    val questionsCount: Int,
    val answerCount: Int
)

fun generateId(): String {
    return UUID.randomUUID().toString()
}
