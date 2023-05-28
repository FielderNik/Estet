package com.culture.estet.domain.models.feed

import androidx.annotation.StringRes
import com.culture.estet.R

enum class NewsCategory {
    DANCE,
    PAINTING,
    THEATRE,
    MUSIC,
    SEMINARS,
    INTERVIEW,
    GRANTS;

    @StringRes
    fun labelSource(): Int {
        return when(this) {
            DANCE -> R.string.label_feed_category_dance
            PAINTING -> R.string.label_feed_category_painting
            THEATRE -> R.string.label_feed_category_theatre
            MUSIC -> R.string.label_feed_category_music
            SEMINARS -> R.string.label_feed_category_seminars
            INTERVIEW -> R.string.label_feed_category_interview
            GRANTS -> R.string.label_feed_category_grants
        }
    }


}