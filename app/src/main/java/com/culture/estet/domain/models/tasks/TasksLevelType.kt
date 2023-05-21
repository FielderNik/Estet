package com.culture.estet.domain.models.tasks

import androidx.annotation.DrawableRes
import com.culture.estet.R

enum class TasksLevelType {
    BEGINNER,
    ADVANCED,
    EXPERT;

    @DrawableRes
    fun iconSource(): Int {
        return when(this) {
            BEGINNER -> R.drawable.icon_beginner
            ADVANCED -> R.drawable.icon_advanced
            EXPERT -> R.drawable.icon_expert
        }
    }
}