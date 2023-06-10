package com.culture.estet.ui.presentation.navigation.initial

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.initial.InitialScreen
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination

object InitialDestination : AppNavigationDestination {
    override val route: String = "initial_route"
    override val destination: String = "initial_destination"

    fun navigationRoute(): String {
        return route
    }
}



fun NavGraphBuilder.createInitialGraph() {
    composable(
        route = InitialDestination.route
    ) { backStackEntry ->

        InitialScreen()
    }
}

fun NavController.navigateToInitial(navOptions: NavOptions? = null) {
    this.navigate(InitialDestination.navigationRoute(), navOptions)
}