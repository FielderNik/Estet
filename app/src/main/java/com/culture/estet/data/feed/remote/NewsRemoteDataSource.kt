package com.culture.estet.data.feed.remote

interface NewsRemoteDataSource {
    suspend fun getNews(): List<NewsResponse>
}