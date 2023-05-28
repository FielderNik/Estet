package com.culture.estet.ui.presentation.feed

import com.culture.estet.data.feed.News
import com.culture.estet.data.feed.NewsItems
import com.culture.estet.domain.models.feed.NewsCategory

sealed class FeedScreenState {
    object Loading : FeedScreenState()
    data class FeedFilled(
        val userId: String,
        val selectedCategory: NewsCategory,
        val newsCategoryList: List<NewsItems>,
        val newsList: List<News>
    ) : FeedScreenState()
}

sealed class FeedEffect {

}

sealed class FeedAction {
    object Initialize : FeedAction()
    data class ChangeCategory(val newsCategory: NewsCategory): FeedAction()
}