package com.culture.estet.domain.models.tasks

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavType
import com.culture.estet.R

enum class TaskLevelType {
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

    @StringRes
    fun stringSource(): Int {
        return when(this) {
            BEGINNER -> R.string.title_questionnaire_choice_level_beginner
            ADVANCED -> R.string.title_questionnaire_choice_level_advanced
            EXPERT -> R.string.title_questionnaire_choice_level_expert
        }
    }

    @DrawableRes
    fun circleSource(): Int {
        return when(this) {
            BEGINNER -> R.drawable.image_pink_circle
            ADVANCED -> R.drawable.image_violet_circle
            EXPERT -> R.drawable.image_dark_violet
        }
    }
}

class NavTasksLevelType: NavType<TaskLevelType>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TaskLevelType {
        return bundle.getSerializable(key) as TaskLevelType
    }

    override fun parseValue(value: String): TaskLevelType {
        return TaskLevelType.valueOf(value)
    }

    override fun put(bundle: Bundle, key: String, value: TaskLevelType) {
        bundle.putSerializable(key, value)
    }

}