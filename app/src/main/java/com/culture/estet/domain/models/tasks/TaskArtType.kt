package com.culture.estet.domain.models.tasks

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import com.culture.estet.R
import com.culture.estet.ui.theme.*

enum class TaskArtType {
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

    fun background(isDarkTheme: Boolean): Color {
        return when(this) {
            MUSIC -> if (isDarkTheme) MusicBgDark else MusicBg
            DANCE -> if (isDarkTheme) DanceBgDark else DanceBg
            THEATRE -> if (isDarkTheme) TheatreBgDark else TheatreBg
            PAINTING -> if (isDarkTheme) PaintingBgDark else PaintingBg
        }
    }
}


class NavTasksArtType: NavType<TaskArtType>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TaskArtType {
        return bundle.getSerializable(key) as TaskArtType
    }

    override fun parseValue(value: String): TaskArtType {
        return TaskArtType.valueOf(value)
    }

    override fun put(bundle: Bundle, key: String, value: TaskArtType) {
        bundle.putSerializable(key, value)
    }
}