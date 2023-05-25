package com.culture.estet.data.feed.repository

import com.culture.estet.data.feed.News
import com.culture.estet.data.feed.remote.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsList(): List<NewsResponse>

    fun observeAll(): Flow<List<News>>
}