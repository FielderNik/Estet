package com.culture.estet.ui.presentation.tournament

import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(

) : BaseViewModel<TournamentScreenState, TournamentEffect, TournamentAction>(TournamentScreenState()) {
    override fun sendAction(action: TournamentAction) {
        launchOnMain {

        }
    }
}