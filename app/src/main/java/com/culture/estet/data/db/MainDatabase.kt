package com.culture.estet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.culture.estet.data.feed.local.NewsDao
import com.culture.estet.data.feed.local.NewsEntity
import com.culture.estet.data.map.local.PointConverter
import com.culture.estet.data.map.local.SchoolEntity
import com.culture.estet.data.map.local.SchoolsDao
import com.culture.estet.data.tasks.answers.local.AnswerDao
import com.culture.estet.data.tasks.answers.local.AnswerEntity
import com.culture.estet.data.tasks.questions.local.QuestionDao
import com.culture.estet.data.tasks.questions.local.QuestionEntity
import com.culture.estet.data.tasks.statistics.local.StatisticsDao
import com.culture.estet.data.tasks.statistics.local.StatisticsEntity

@Database(
    entities = [
        NewsEntity::class,
        SchoolEntity::class,
        QuestionEntity::class,
        AnswerEntity::class,
        StatisticsEntity::class,
    ], version = 4
)
@TypeConverters(PointConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun schoolsDao(): SchoolsDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
    abstract fun statisticsDao(): StatisticsDao


//    companion object {
//        @Volatile
//        private var INSTANCE: MainDatabase? = null
//
//        fun getInstance(context: Context): MainDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MainDatabase::class.java,
//                    "MainDB"
//                ).fallbackToDestructiveMigration().build()
//
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}