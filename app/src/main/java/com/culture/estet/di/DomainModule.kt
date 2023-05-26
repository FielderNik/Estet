package com.culture.estet.di

import com.culture.estet.core.network.connection.ConnectivityObserver
import com.culture.estet.core.network.connection.NetworkConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    @Singleton
    fun bindConnectivityObserver(connectivityObserver: NetworkConnectivityObserver): ConnectivityObserver
}