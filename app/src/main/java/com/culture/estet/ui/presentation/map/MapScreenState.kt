package com.culture.estet.ui.presentation.map

import com.culture.estet.domain.models.tasks.TaskCategory


sealed class MapScreenState {

    object Available : MapScreenState()

    object Unavailable : MapScreenState()
}

sealed class MapEffect {

}


sealed class MapAction {
    object Initialize : MapAction()
}