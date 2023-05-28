package com.culture.estet.ui.presentation.navigation.feed

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.feed.FeedScreen
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination

object FeedDestination : AppNavigationDestination {
    override val route: String = "feed_route"
    override val destination: String = "feed_destination"

    fun navigationRoute(): String {
        return route
    }
}


fun NavGraphBuilder.createFeedGraph() {
    composable(
        route = FeedDestination.route
    ) { backStackEntry ->

        FeedScreen()
    }
}

fun NavController.navigateToFeed(navOptions: NavOptions? = null) {
    this.navigate(FeedDestination.navigationRoute(), navOptions)
}