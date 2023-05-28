package com.culture.estet.ui.presentation.navigation.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination
import com.culture.estet.ui.presentation.profile.ProfileScreen

object ProfileDestination : AppNavigationDestination {
    override val route: String = "profile_route"
    override val destination: String = "profile_destination"

    fun navigationRoute(): String {
        return route
    }
}

fun NavGraphBuilder.createProfileGraph() {
    composable(
        route = ProfileDestination.route
    ) { backStackEntry ->

        ProfileScreen()
    }
}
