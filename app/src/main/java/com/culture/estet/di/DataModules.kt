package com.culture.estet.di

import com.culture.estet.core.network.EstetApi
import com.culture.estet.data.feed.remote.NewsRemoteDataSource
import com.culture.estet.data.feed.remote.RetrofitNewsRemoteDataSource
import com.culture.estet.data.map.remote.RetrofitSchoolsRemoteDataSource
import com.culture.estet.data.map.remote.SchoolsApi
import com.culture.estet.data.map.remote.SchoolsRemoteDataSource
import com.culture.estet.data.tasks.answers.remote.AnswerRemoteDataSource
import com.culture.estet.data.tasks.answers.remote.AnswerRemoteDataSourceImpl
import com.culture.estet.data.tasks.questions.remote.QuestionRemoteDataSource
import com.culture.estet.data.tasks.questions.remote.QuestionRemoteDataSourceImpl
import com.culture.estet.data.tasks.statistics.remote.StatisticsRemoteDataSource
import com.culture.estet.data.tasks.statistics.remote.StatisticsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideNewsNetworkDataSource(api: EstetApi): NewsRemoteDataSource {
        return RetrofitNewsRemoteDataSource(api)
    }
    @Provides
    @Singleton
    fun provideSchoolsNetworkDataSource(api: SchoolsApi): SchoolsRemoteDataSource {
        return RetrofitSchoolsRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideQuestionsNetworkDataSource(api: EstetApi): QuestionRemoteDataSource {
        return QuestionRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideAnswerNetworkDataSource(api: EstetApi): AnswerRemoteDataSource {
        return AnswerRemoteDataSourceImpl(api)
    }

    @Provides
    @Singleton
    fun provideStatisticsNetworkDataSource(api: EstetApi): StatisticsRemoteDataSource {
        return StatisticsRemoteDataSourceImpl(api)
    }
}