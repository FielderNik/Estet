package com.culture.estet.ui.presentation.map

import com.culture.estet.core.network.connection.ConnectivityObserver
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
): BaseViewModel<MapScreenState, MapEffect, MapAction>(MapScreenState.Available) {

    init {
        launchOnMain {
            connectivityObserver.observe().collect(this::handleNetworkStatus)
        }
    }

    override fun sendAction(action: MapAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: MapScreenState, action: MapAction) {
        when(currentState) {
            is MapScreenState.Loading -> {
                handleActionLoadingState(action)
            }
            is MapScreenState.Error -> {
                handleActionErrorState(action)
            }
        }
    }

    private suspend fun handleActionLoadingState(action: MapAction) {
        when(action) {
            is
        }
    }

    private suspend fun handleActionErrorState(action: MapAction) {
        when(action) {

        }
    }

    private suspend fun handleNetworkStatus(status: ConnectivityObserver.Status) {
        when(status) {
            ConnectivityObserver.Status.AVAILABLE -> setState(MapScreenState.Available)
            ConnectivityObserver.Status.UNAVAILABLE -> setState(MapScreenState.Unavailable)
            else -> Unit
        }
    }

}