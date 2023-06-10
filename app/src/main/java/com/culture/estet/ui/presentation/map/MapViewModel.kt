package com.culture.estet.ui.presentation.map

import android.util.Log
import com.culture.estet.core.network.connection.ConnectivityObserver
import com.culture.estet.data.map.School
import com.culture.estet.data.map.mock.getMockSchools
import com.culture.estet.data.map.repository.SchoolsRepository
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val schoolRepository: SchoolsRepository,
): BaseViewModel<MapScreenState, MapEffect, MapAction>(MapScreenState.Available(emptyList())) {
    init {
        launchOnMain {
            handleNetworkStatus(connectivityObserver.currentConnection())
            connectivityObserver.observe().collect(this::handleNetworkStatus)
        }
    }

    override fun sendAction(action: MapAction) {
    }

    private suspend fun handleNetworkStatus(status: ConnectivityObserver.Status) {
        Log.v("~", status.name)
        when (status) {
            ConnectivityObserver.Status.AVAILABLE -> setState(MapScreenState.Available(getSchools()))
            ConnectivityObserver.Status.UNAVAILABLE -> setState(MapScreenState.Unavailable(getSchools()))
            ConnectivityObserver.Status.LOST -> setState(MapScreenState.Unavailable(getSchools()))
            else -> Unit
        }
    }

    private suspend fun getSchools(): List<School> {
        return schoolRepository.getSchoolsList()
    }

}