package com.culture.estet.domain.models.tasks

import com.culture.estet.domain.models.ArtType

data class Task(
    val id: String,
    val type: ArtType,
    val level: TaskLevelType,
    val questionsCount: Int,
    val answerCount: Int
)
