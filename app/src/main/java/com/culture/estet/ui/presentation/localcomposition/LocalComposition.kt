package com.culture.estet.ui.presentation.localcomposition

import androidx.compose.runtime.staticCompositionLocalOf
import com.culture.estet.ui.presentation.states.AppScreenState
import com.culture.estet.ui.presentation.states.AppTopBarState

val LocalAppScreenState = staticCompositionLocalOf<AppScreenState> {
    error("AppScreenState not provided")
}

val LocalAppTopBarState = staticCompositionLocalOf<AppTopBarState> {
    error("AppTopBarState not provided")
}