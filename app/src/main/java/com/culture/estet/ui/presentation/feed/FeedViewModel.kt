package com.culture.estet.ui.presentation.feed

import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor() : BaseViewModel<FeedScreenState, FeedEffect, FeedAction>(FeedScreenState.FeedFilled("")) {
    override fun sendAction(action: FeedAction) {
        launchOnMain {

        }
    }
}