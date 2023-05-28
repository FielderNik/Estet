package com.culture.estet.ui.presentation.tournament

import com.culture.estet.domain.models.tournament.User
import com.culture.estet.ui.presentation.tournament.model.TournamentFilter

sealed class TournamentScreenState {

    object Loading : TournamentScreenState()

    data class TournamentState(
        val userId: String,
        val users: List<User>,
        val selectedFilter: TournamentFilter,
    ) : TournamentScreenState()
}

sealed class TournamentEffect {

}

sealed class TournamentAction {
    data class Initialize(val userId: String) : TournamentAction()
    data class ChangeFilter(val filter: TournamentFilter) : TournamentAction()
}