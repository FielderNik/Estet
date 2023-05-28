package com.culture.estet.core.utils

import androidx.annotation.StringRes
import com.culture.estet.R

@StringRes
fun scoreText(score: Int): Int {
    return when(score) {
        1, 21, 31, 41, 51, 61 -> R.string.title_score_one
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44 -> R.string.title_score_two
        else -> R.string.title_score_many
    }
}