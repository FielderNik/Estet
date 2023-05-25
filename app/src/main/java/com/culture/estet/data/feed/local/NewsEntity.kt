package com.culture.estet.data.feed.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.culture.estet.data.feed.local.NewsEntity.Companion.NEWS_TABLE_NAME
import com.culture.estet.core.generateId

@Entity(tableName = NEWS_TABLE_NAME)
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = generateId(),

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "subtitle")
    val subtitle: String,

    @ColumnInfo(name = "date")
    val date: String

) {
    companion object {
        const val NEWS_TABLE_NAME = "news_entities_table"
    }
}