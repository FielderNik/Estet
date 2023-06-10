package com.culture.estet.di

import android.content.Context
import androidx.room.Room
import com.culture.estet.data.db.MainDatabase
import com.culture.estet.data.feed.local.NewsDao
import com.culture.estet.data.map.local.SchoolsDao
import com.culture.estet.data.tasks.answers.local.AnswerDao
import com.culture.estet.data.tasks.questions.local.QuestionDao
import com.culture.estet.data.tasks.statistics.local.StatisticsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideEstetDatabase(@ApplicationContext context: Context): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "estet_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideNewsDao(mainDatabase: MainDatabase): NewsDao = mainDatabase.newsDao()

    @Provides
    fun provideSchoolDao(mainDatabase: MainDatabase): SchoolsDao = mainDatabase.schoolsDao()

    @Provides
    fun provideQuestionDao(mainDatabase: MainDatabase): QuestionDao = mainDatabase.questionDao()

    @Provides
    fun provideAnswerDao(mainDatabase: MainDatabase): AnswerDao = mainDatabase.answerDao()

    @Provides
    fun provideStatisticsDao(mainDatabase: MainDatabase): StatisticsDao = mainDatabase.statisticsDao()

}