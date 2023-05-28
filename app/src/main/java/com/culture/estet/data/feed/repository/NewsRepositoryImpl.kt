package com.culture.estet.data.feed.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.feed.BaseNews
import com.culture.estet.data.feed.News
import com.culture.estet.data.feed.NewsItems
import com.culture.estet.data.mock.FeedNews
import com.culture.estet.domain.models.feed.NewsCategory
import com.culture.estet.domain.repository.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(): NewsRepository, BaseRepository() {

    override suspend fun getNewsList(): Either<Failure, List<NewsItems>> {
        return handleRequest {
            val news = FeedNews.newsList
            val birthdaysList = FeedNews.birthdaysList
            val interviewList = FeedNews.interviewList

            val result = mutableListOf<NewsItems>()
            result.add(NewsItems.NewsList(news.subList(0, 2)))
            result.add(NewsItems.BirthdaysList(birthdaysList))
            result.add(NewsItems.NewsList(news.subList(2, 3)))
            result.add(NewsItems.InterviewList(interviewList))
            result.add(NewsItems.NewsList(news.subList(3, news.lastIndex)))

            result
        }

    }

    override suspend fun getInterviewList(): Either<Failure, List<BaseNews>> {
        return handleRequest {
            FeedNews.interviewList
        }
    }

    override suspend fun getFilteredNewsList(category: NewsCategory): Either<Failure, List<BaseNews>> {
        return handleRequest {
            FeedNews.newsList.filter { it.category == category }
        }
    }

    override fun observeAll(): Flow<List<News>> {
        return flowOf()
    }
}