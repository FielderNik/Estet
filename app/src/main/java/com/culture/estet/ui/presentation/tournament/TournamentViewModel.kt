package com.culture.estet.ui.presentation.tournament

import com.culture.estet.core.funcional.onFailure
import com.culture.estet.core.funcional.onSuccess
import com.culture.estet.domain.models.tournament.User
import com.culture.estet.domain.repository.TournamentRepository
import com.culture.estet.ui.presentation.base.BaseViewModel
import com.culture.estet.ui.presentation.tournament.model.TournamentFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TournamentViewModel @Inject constructor(
    private val tournamentRepository: TournamentRepository
) : BaseViewModel<TournamentScreenState, TournamentEffect, TournamentAction>(TournamentScreenState.Loading) {

    private var users: List<User> = emptyList()

    override fun sendAction(action: TournamentAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: TournamentScreenState, action: TournamentAction) {
        when(currentState) {
            is TournamentScreenState.TournamentState -> {
                handleActionsTournamentState(currentState, action)
            }
            TournamentScreenState.Loading -> {
                handleActionLoadingState(currentState, action)
            }
        }
    }

    private suspend fun handleActionLoadingState(currentState: TournamentScreenState, action: TournamentAction) {
        when(action) {
            is TournamentAction.ChangeFilter -> Unit
            is TournamentAction.Initialize -> {
                loadUsers(action.userId)
            }
        }

    }

    private suspend fun loadUsers(userId: String, filter: TournamentFilter? = null) {
        withIo {
            val artType = filter?.getArtTypeByFilter()
            tournamentRepository.getUsersByArtType(userId, artType)
        }
            .onFailure {
                //todo
            }
            .onSuccess {
                users = it
                updateTournamentState(userId = userId, filter = filter)
            }
    }

    private suspend fun updateTournamentState(userId: String, filter: TournamentFilter? = null) {
        val sortedUsers = if (filter == null || filter == TournamentFilter.ALL) {
            users.sortedByDescending { it.totalScore }
        } else {
            users.sortedByDescending { user -> user.scoreArt?.score }
        }

        setState(TournamentScreenState.TournamentState(userId = userId, users = sortedUsers, filter ?: TournamentFilter.ALL))
    }

    private suspend fun handleActionsTournamentState(currentState: TournamentScreenState.TournamentState, action: TournamentAction) {
        when(action) {
            is TournamentAction.ChangeFilter -> {
                loadUsers(currentState.userId, action.filter)
            }
            is TournamentAction.Initialize -> Unit
        }
    }


}