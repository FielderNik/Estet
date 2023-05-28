package com.culture.estet.ui.presentation.navigation.tournament

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination
import com.culture.estet.ui.presentation.tournament.TournamentScreen

object TournamentDestination : AppNavigationDestination {
    override val route: String = "tournament_route"
    override val destination: String = "tournament_destination"

    fun navigationRoute(): String {
        return route
    }
}

fun NavGraphBuilder.createTournamentGraph() {
    composable(
        route = TournamentDestination.route
    ) { backStackEntry ->

        TournamentScreen()
    }
}

fun NavController.navigateToTournament(navOptions: NavOptions? = null) {
    this.navigate(TournamentDestination.navigationRoute(), navOptions)
}