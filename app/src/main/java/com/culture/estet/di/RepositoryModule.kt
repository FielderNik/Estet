package com.culture.estet.di

import com.culture.estet.domain.repository.QuestionRepository
import com.culture.estet.domain.repository.QuestionRepositoryImpl
import com.culture.estet.domain.repository.TaskRepository
import com.culture.estet.domain.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindQuestionRepository(questionRepositoryImpl: QuestionRepositoryImpl): QuestionRepository

    @Binds
    fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository
}