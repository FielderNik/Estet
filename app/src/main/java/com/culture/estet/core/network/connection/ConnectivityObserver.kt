package com.culture.estet.core.network.connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>

    fun currentConnection(): Status

    enum class Status {
        AVAILABLE, UNAVAILABLE, LOSING, LOST
    }
}