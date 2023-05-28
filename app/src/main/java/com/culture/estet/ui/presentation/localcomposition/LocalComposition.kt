package com.culture.estet.ui.presentation.localcomposition

import androidx.compose.runtime.staticCompositionLocalOf
import com.culture.estet.core.eventbus.BottomSheetEventBus
import com.culture.estet.core.eventbus.DialogEventBus
import com.culture.estet.ui.presentation.states.AppScreenState
import com.culture.estet.ui.presentation.states.AppTopBarState

val LocalAppScreenState = staticCompositionLocalOf<AppScreenState> {
    error("AppScreenState not provided")
}

val LocalAppTopBarState = staticCompositionLocalOf<AppTopBarState> {
    error("AppTopBarState not provided")
}

val LocalDialogEventBus = staticCompositionLocalOf<DialogEventBus> {
    error("DialogEventBus not provided")
}

val LocalBottomSheetEventBus = staticCompositionLocalOf<BottomSheetEventBus> {
    error("BottomSheetEventBus not provided")
}