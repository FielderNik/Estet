package com.culture.estet.ui.presentation.tournament.model

import androidx.annotation.StringRes
import com.culture.estet.R
import com.culture.estet.domain.models.ArtType

enum class TournamentFilter {
    ALL,
    DANCE,
    PAINTING,
    THEATRE,
    MUSIC;

    @StringRes
    fun labelSource(): Int {
        return when(this) {
            ALL -> R.string.label_tournament_filter_all
            DANCE -> R.string.label_tournament_filter_dance
            PAINTING -> R.string.label_tournament_filter_painting
            THEATRE -> R.string.label_tournament_filter_theatre
            MUSIC -> R.string.label_tournament_filter_music
        }
    }

    fun getArtTypeByFilter(): ArtType? {
        return when(this) {
            ALL -> null
            DANCE -> ArtType.DANCE
            PAINTING -> ArtType.PAINTING
            THEATRE -> ArtType.THEATRE
            MUSIC -> ArtType.MUSIC
        }
    }
}