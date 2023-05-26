package com.culture.estet.data.feed.remote

import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(): NewsRemoteDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        TODO("Not yet implemented")
    }
}