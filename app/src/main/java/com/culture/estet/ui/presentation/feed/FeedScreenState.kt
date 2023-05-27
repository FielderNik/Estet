package com.culture.estet.ui.presentation.feed

sealed class FeedScreenState {
    object Loading : FeedScreenState()
    data class FeedFilled(val userId: String) : FeedScreenState()
}

sealed class FeedEffect {

}

sealed class FeedAction {

}