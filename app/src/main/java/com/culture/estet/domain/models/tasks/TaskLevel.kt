package com.culture.estet.domain.models.tasks

import com.culture.estet.domain.models.ArtType

data class TaskLevel(
    val artType: ArtType,
    val taskLevelType: TaskLevelType,
    val amountQuestions: Int,
    val completedQuestions: Int,
)
