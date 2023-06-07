package com.culture.estet.di

import android.content.Context
import com.culture.estet.data.db.MainDatabase
import com.culture.estet.data.feed.local.NewsDao
import com.culture.estet.data.feed.remote.NewsRemoteDataSource
import com.culture.estet.data.feed.remote.RetrofitNewsRemoteDataSource
import com.culture.estet.data.map.local.SchoolsDao
import com.culture.estet.data.map.remote.RetrofitSchoolsRemoteDataSource
import com.culture.estet.data.map.remote.SchoolsRemoteDataSource
import com.culture.estet.domain.repository.settings.AppSettingsRepository
import com.culture.estet.domain.repository.settings.AppSettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindNewsNetworkDataSource(dataSource: RetrofitNewsRemoteDataSource): NewsRemoteDataSource
    @Binds
    @Singleton
    fun bindSchoolsNetworkDataSource(dataSource: RetrofitSchoolsRemoteDataSource): SchoolsRemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideNewsDao(@ApplicationContext context: Context): NewsDao = MainDatabase.getInstance(context).newsDao()
    @Provides
    fun provideSchoolDao(@ApplicationContext context: Context): SchoolsDao = MainDatabase.getInstance(context).schoolsDao()

    @Provides
    fun provideAppSettingsRepository(@ApplicationContext context: Context): AppSettingsRepository {
        return AppSettingsRepositoryImpl(context)
    }
}