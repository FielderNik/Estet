package com.culture.estet.ui.presentation.events

data class EventsScreenState(
    val events: List<String> = emptyList()
)

sealed interface EventsEffects {

}


sealed interface EventsAction {
    object Initialize : EventsAction
}