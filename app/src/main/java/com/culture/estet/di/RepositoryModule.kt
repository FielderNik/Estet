package com.culture.estet.di

import com.culture.estet.data.feed.repository.NewsRepository
import com.culture.estet.data.feed.repository.NewsRepositoryImpl
import com.culture.estet.data.map.repository.SchoolsRepository
import com.culture.estet.data.map.repository.SchoolsRepositoryImpl
import com.culture.estet.domain.repository.*
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
    fun bindAnswerRepository(answerRepositoryImpl: AnswerRepositoryImpl): AnswerRepository

    @Binds
    @Singleton
    fun bindStatisticRepository(statisticsRepositoryImpl: StatisticsRepositoryImpl): StatisticsRepository

    @Binds
    @Singleton
    fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository

    @Binds
    @Singleton
    fun bindNewsRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    @Singleton
    fun bindSchoolsRepository(repository: SchoolsRepositoryImpl): SchoolsRepository

    @Binds
    @Singleton
    fun bindTournamentRepository(repository: TournamentRepositoryImpl): TournamentRepository


    @Binds
    @Singleton
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
}