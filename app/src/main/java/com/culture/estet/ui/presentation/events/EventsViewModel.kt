package com.culture.estet.ui.presentation.events

import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor() : BaseViewModel<EventsScreenState, EventsEffects, EventsAction>(EventsScreenState()) {

    override fun sendAction(action: EventsAction) {
        launchOnMain {
            when(action) {
                EventsAction.Initialize -> {
                    val updatedState = state.value.copy(events = listOf("one", "two", "apple", "orange", "ibm", "android", "hello", "world"))
                    setState(updatedState)
                }
            }
        }
    }
}