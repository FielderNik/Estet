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
    fun circleSourceByArtType(artType: TaskArtType): Int {
        return when(this) {
            BEGINNER -> beginnerCircleSource(artType)
            ADVANCED -> advancedCircleSource(artType)
            EXPERT -> expertCircleSource(artType)
        }
    }

    @DrawableRes
    private fun beginnerCircleSource(artType: TaskArtType): Int {
        return when(artType) {
            TaskArtType.MUSIC -> R.drawable.image_green_light_circle
            TaskArtType.DANCE -> R.drawable.image_green_light_variant
            TaskArtType.THEATRE -> R.drawable.image_pink_circle
            TaskArtType.PAINTING -> R.drawable.image_pink_circle
        }
    }

    @DrawableRes
    private fun advancedCircleSource(artType: TaskArtType): Int {
        return when(artType) {
            TaskArtType.MUSIC -> R.drawable.image_green_medium_circle
            TaskArtType.DANCE -> R.drawable.image_green_circle
            TaskArtType.THEATRE -> R.drawable.image_violet_circle
            TaskArtType.PAINTING -> R.drawable.image_pink_dark_circle
        }
    }

    @DrawableRes
    private fun expertCircleSource(artType: TaskArtType): Int {
        return when(artType) {
            TaskArtType.MUSIC -> R.drawable.image_green_variant_circle
            TaskArtType.DANCE -> R.drawable.image_blue_dark_circle
            TaskArtType.THEATRE -> R.drawable.image_violet_dark
            TaskArtType.PAINTING -> R.drawable.image_violet_dark
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