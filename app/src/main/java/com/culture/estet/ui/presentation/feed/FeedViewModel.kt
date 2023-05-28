package com.culture.estet.ui.presentation.feed

import com.culture.estet.core.funcional.onFailure
import com.culture.estet.core.funcional.onSuccess
import com.culture.estet.data.feed.repository.NewsRepository
import com.culture.estet.domain.models.feed.NewsCategory
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseViewModel<FeedScreenState, FeedEffect, FeedAction>(FeedScreenState.Loading) {

    override fun sendAction(action: FeedAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: FeedScreenState, action: FeedAction) {
        when(currentState) {
            is FeedScreenState.FeedFilled -> {
                handleActionsFeedFilledState(currentState, action)
            }
            is FeedScreenState.Loading -> {
                handleActionLoadingState(currentState, action)
            }
            is FeedScreenState.FilteredNews -> {
                handleActionFilteredState(currentState, action)
            }
        }
    }

    private suspend fun handleActionFilteredState(currentState: FeedScreenState.FilteredNews, action: FeedAction) {
        when(action) {
            is FeedAction.ChangeCategory -> {
                val currentCategory = currentState.selectedCategory
                if (currentCategory == action.newsCategory) {
                    loadFeedNews()
                } else {
                    loadFilteredNews(action.newsCategory)
                }
            }
            FeedAction.Initialize -> Unit
        }
    }

    private suspend fun handleActionsFeedFilledState(currentState: FeedScreenState.FeedFilled, action: FeedAction) {
        when(action) {
            is FeedAction.ChangeCategory -> {
                loadFilteredNews(action.newsCategory)
            }
            FeedAction.Initialize -> Unit
        }

    }

    private suspend fun handleActionLoadingState(currentState: FeedScreenState.Loading, action: FeedAction) {
        when(action) {
            is FeedAction.ChangeCategory -> {
                loadFilteredNews(action.newsCategory)
            }
            FeedAction.Initialize -> {
                loadFeedNews()
            }
        }

    }

    private suspend fun loadFeedNews() {
        withIo {
            newsRepository.getNewsList()
        }
            .onFailure {

            }
            .onSuccess {
                setState(FeedScreenState.FeedFilled(selectedCategory = null, newsCategoryList = it))
            }
    }

    private suspend fun loadFilteredNews(category: NewsCategory) {
        if (category == NewsCategory.INTERVIEW) {
            loadInterviews()
        } else {
            loadNewsByCategory(category)
        }
    }

    private suspend fun loadNewsByCategory(category: NewsCategory) {
        withIo {
            newsRepository.getFilteredNewsList(category)
        }
            .onFailure {

            }
            .onSuccess {
                setState(FeedScreenState.FilteredNews(selectedCategory = category, newsCategoryList = it))
            }
    }


    private suspend fun loadInterviews() {
        withIo {
            newsRepository.getInterviewList()
        }
            .onFailure {

            }
            .onSuccess {
                setState(FeedScreenState.FilteredNews(selectedCategory = NewsCategory.INTERVIEW, newsCategoryList = it))
            }
    }

}