package com.culture.estet.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.culture.estet.data.feed.local.NewsDao
import com.culture.estet.data.feed.local.NewsEntity
import com.culture.estet.data.map.local.PointConverter
import com.culture.estet.data.map.local.SchoolEntity
import com.culture.estet.data.map.local.SchoolsDao

@Database(entities = [NewsEntity::class, SchoolEntity::class], version = 2)
@TypeConverters(PointConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun schoolsDao(): SchoolsDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "MainDB"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}