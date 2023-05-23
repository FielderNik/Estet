package com.culture.estet.domain.models.tasks

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.navigation.NavType
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

class NavTasksLevelType: NavType<TasksLevelType>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TasksLevelType {
        return bundle.getSerializable(key) as TasksLevelType
    }

    override fun parseValue(value: String): TasksLevelType {
        return TasksLevelType.valueOf(value)
    }

    override fun put(bundle: Bundle, key: String, value: TasksLevelType) {
        bundle.putSerializable(key, value)
    }

}