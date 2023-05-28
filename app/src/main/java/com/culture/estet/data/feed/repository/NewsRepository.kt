package com.culture.estet.data.feed.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.feed.BaseNews
import com.culture.estet.data.feed.News
import com.culture.estet.data.feed.NewsItems
import com.culture.estet.domain.models.feed.NewsCategory
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsList(): Either<Failure, List<NewsItems>>
    suspend fun getInterviewList(): Either<Failure, List<BaseNews>>
    suspend fun getFilteredNewsList(category: NewsCategory): Either<Failure, List<BaseNews>>

    fun observeAll(): Flow<List<News>>
}