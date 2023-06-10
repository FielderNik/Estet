package com.culture.estet.domain.models

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import com.culture.estet.R
import com.culture.estet.ui.theme.*

enum class ArtType(val id: Int) {
    MUSIC(1),
    DANCE(2),
    THEATRE(3),
    PAINTING(4);

    @DrawableRes
    fun painterSource(): Int {
        return when (this) {
            MUSIC -> R.drawable.image_music
            DANCE -> R.drawable.image_dance
            THEATRE -> R.drawable.image_theatre
            PAINTING -> R.drawable.image_painting
        }
    }

    @DrawableRes
    fun pointSchoolSource(): Int {
        return when (this) {
            MUSIC -> R.drawable.school_point_music
            DANCE -> R.drawable.school_point_dance
            THEATRE -> R.drawable.school_point_theater
            PAINTING -> R.drawable.school_point_painting
        }
    }

    @StringRes
    fun stringSource(): Int {
        return when (this) {
            MUSIC -> R.string.title_music
            DANCE -> R.string.title_dance
            THEATRE -> R.string.title_theatre
            PAINTING -> R.string.title_painting
        }
    }

    fun background(isDarkTheme: Boolean): Color {
        return when (this) {
            MUSIC -> if (isDarkTheme) MusicBgDark else MusicBg
            DANCE -> if (isDarkTheme) DanceBgDark else DanceBg
            THEATRE -> if (isDarkTheme) TheatreBgDark else TheatreBg
            PAINTING -> if (isDarkTheme) PaintingBgDark else PaintingBg
        }
    }

    companion object {
        fun getById(id: Int): ArtType {
//            return ArtType.values()[id % ArtType.values().size]
            return ArtType.values().find { it.id == id } ?: ArtType.DANCE
        }
    }
}


class NavTasksArtType : NavType<ArtType>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ArtType {
        return bundle.getSerializable(key) as ArtType
    }

    override fun parseValue(value: String): ArtType {
        return ArtType.valueOf(value)
    }

    override fun put(bundle: Bundle, key: String, value: ArtType) {
        bundle.putSerializable(key, value)
    }
}