package com.culture.estet.data.feed.remote

import javax.inject.Inject

class RetrofitNewsRemoteDataSource @Inject constructor(): NewsRemoteDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        TODO("Not yet implemented")
    }
}