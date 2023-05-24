package com.culture.estet.domain.models.tasks

import java.util.UUID

data class Task(
    val id: String,
    val type: TaskArtType,
    val level: TaskLevelType,
    val questionsCount: Int,
    val answerCount: Int
)

fun generateId(): String {
    return UUID.randomUUID().toString()
}
