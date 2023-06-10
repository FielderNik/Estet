package com.culture.estet.di

import android.content.Context
import com.culture.estet.domain.repository.settings.AppSettingsRepository
import com.culture.estet.domain.repository.settings.AppSettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    fun provideAppSettingsRepository(@ApplicationContext context: Context): AppSettingsRepository {
        return AppSettingsRepositoryImpl(context)
    }
}