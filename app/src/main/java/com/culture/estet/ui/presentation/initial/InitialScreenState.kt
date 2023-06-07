package com.culture.estet.ui.presentation.initial

object InitialScreenState

sealed class InitialEffect {
    object Loaded : InitialEffect()
}

sealed class InitialAction {
    object Initialize : InitialAction()
}