package com.culture.estet.di

import com.culture.estet.data.feed.repository.NewsRepository
import com.culture.estet.data.feed.repository.NewsRepositoryImpl
import com.culture.estet.domain.repository.QuestionRepository
import com.culture.estet.domain.repository.QuestionRepositoryImpl
import com.culture.estet.domain.repository.TaskRepository
import com.culture.estet.domain.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl): QuestionRepository

    @Binds
    @Singleton
    fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository

    @Binds
    @Singleton
    fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository
}