package com.culture.estet.domain.models.tasks

data class TaskLevel(
    val taskArtType: TaskArtType,
    val taskLevelType: TaskLevelType,
    val amountQuestions: Int,
    val completedQuestions: Int,
)
