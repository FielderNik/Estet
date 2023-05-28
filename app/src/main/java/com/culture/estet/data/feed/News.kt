package com.culture.estet.data.feed

import com.culture.estet.domain.models.feed.NewsCategory

data class News(
    val id: String,
    val title: String,
    val subtitle: String?,
    val date: String,
    val category: NewsCategory,
    val imageUrl: String,
    val newsUrl: String,
//    val newsType: NewsType,
)

data class Birthday(
    val id: String,
    val title: String,
    val imageUrl: String,
)


sealed class NewsItems {
    data class TitleNews(val title: String) : NewsItems()
    data class NewsList(val newsList: List<News>) : NewsItems()
    data class BirthdaysList(val birthdaysList: List<Birthday>) : NewsItems()
}