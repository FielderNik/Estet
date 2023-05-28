package com.culture.estet.ui.presentation.map

import com.culture.estet.data.map.School


sealed class MapScreenState {

    class Available(
        val schools: List<School>
    ): MapScreenState()

    data class Unavailable(
        val schools: List<School>
    ) : MapScreenState()
}

sealed class MapEffect {

}

sealed class MapAction {
    object Initialize: MapAction()
}