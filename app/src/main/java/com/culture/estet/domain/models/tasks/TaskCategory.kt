package com.culture.estet.domain.models.tasks

import com.culture.estet.domain.models.ArtType

data class TaskCategory(
    val id: String,
    val type: ArtType,
    val amountLevels: Int,
    val completedLevels: Int,
    val amountArtScore: Int,
    val ordinal: Int,
)
