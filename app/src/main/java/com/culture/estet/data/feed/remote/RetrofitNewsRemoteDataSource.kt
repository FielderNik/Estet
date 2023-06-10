package com.culture.estet.data.feed.remote

import com.culture.estet.core.network.EstetApi
import javax.inject.Inject

class RetrofitNewsRemoteDataSource @Inject constructor(api: EstetApi): NewsRemoteDataSource {
    override suspend fun getNews(): List<NewsResponse> {
        TODO("Not yet implemented")
    }
}