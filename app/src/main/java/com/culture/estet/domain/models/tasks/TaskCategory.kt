package com.culture.estet.domain.models.tasks

data class TaskCategory(
    val id: String,
    val type: TaskArtType,
    val amountLevels: Int,
    val completedLevels: Int,
    val amountArtScore: Int,
    val ordinal: Int,
)
