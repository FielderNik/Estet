package com.culture.estet.data.feed.repository

import com.culture.estet.data.feed.News
import com.culture.estet.data.feed.local.NewsDao
import com.culture.estet.data.feed.remote.NewsRemoteDataSource
import com.culture.estet.data.feed.remote.NewsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val localDataSource: NewsDao,
    private val remoteDataSource: NewsRemoteDataSource,
): NewsRepository {

    override suspend fun getNewsList(): List<NewsResponse> = remoteDataSource.getNews()

    override fun observeAll(): Flow<List<News>> {
//        return localDataSource.observeAll().map { news ->
//            news.toNewsList()
//        }

        return flowOf()
    }
}