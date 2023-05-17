package com.culture.estet.ui.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.events.EventsScreen


object EventsDestination : AppNavigationDestination {
    override val route: String = "events_route"
    override val destination: String = "events_destination"
}

fun NavGraphBuilder.eventsGraph() {
    composable(route = EventsDestination.route) {

        EventsScreen()
    }
}