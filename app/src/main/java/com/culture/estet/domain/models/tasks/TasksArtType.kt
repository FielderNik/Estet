package com.culture.estet.domain.models.tasks

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import com.culture.estet.R
import com.culture.estet.ui.theme.DanceBg
import com.culture.estet.ui.theme.MusicBg
import com.culture.estet.ui.theme.PaintingBg
import com.culture.estet.ui.theme.TheatreBg

enum class TasksArtType {
    MUSIC,
    DANCE,
    THEATRE,
    PAINTING;

    @DrawableRes
    fun painterSource(): Int {
        return when(this) {
            MUSIC -> R.drawable.image_music
            DANCE -> R.drawable.image_dance
            THEATRE -> R.drawable.image_theatre
            PAINTING -> R.drawable.image_painting
        }
    }

    @StringRes
    fun stringSource(): Int {
        return when(this) {
            MUSIC -> R.string.title_music
            DANCE -> R.string.title_dance
            THEATRE -> R.string.title_theatre
            PAINTING -> R.string.title_painting
        }
    }

    fun background(): Color {
        return when(this) {
            MUSIC -> MusicBg
            DANCE -> DanceBg
            THEATRE -> TheatreBg
            PAINTING -> PaintingBg
        }
    }
}


class NavTasksArtType: NavType<TasksArtType>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TasksArtType {
        return bundle.getSerializable(key) as TasksArtType
    }

    override fun parseValue(value: String): TasksArtType {
        return TasksArtType.valueOf(value)
    }

    override fun put(bundle: Bundle, key: String, value: TasksArtType) {
        bundle.putSerializable(key, value)
    }
}