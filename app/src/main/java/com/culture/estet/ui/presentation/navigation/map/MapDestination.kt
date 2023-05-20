package com.culture.estet.ui.presentation.navigation.map

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.map.MapScreen
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination

object MapDestination : AppNavigationDestination {
    override val route: String = "map_route"
    override val destination: String = "map_destination"

    fun navigationRoute(): String {
        return route
    }
}

fun NavGraphBuilder.createMapGraph() {
    composable(
        route = MapDestination.route
    ) { backStackEntry ->

        MapScreen()
    }
}

fun NavController.navigateToMap(navOptions: NavOptions? = null) {
    this.navigate(MapDestination.navigationRoute(), navOptions)
}