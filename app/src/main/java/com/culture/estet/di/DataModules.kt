package com.culture.estet.di

import android.content.Context
import com.culture.estet.data.db.MainDatabase
import com.culture.estet.data.feed.repository.NewsRepository
import com.culture.estet.data.feed.repository.NewsRepositoryImpl
import com.culture.estet.data.feed.local.NewsDao
import com.culture.estet.data.feed.remote.NewsRemoteDataSource
import com.culture.estet.data.feed.remote.RetrofitRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: RetrofitRemoteDataSource): NewsRemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideTaskDao(@ApplicationContext context: Context): NewsDao = MainDatabase.getInstance(context).newsDao()
}