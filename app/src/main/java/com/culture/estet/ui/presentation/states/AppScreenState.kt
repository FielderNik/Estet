package com.culture.estet.ui.presentation.states

import android.os.Trace
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.culture.estet.ui.presentation.navigation.TopLevelDestination
import com.culture.estet.ui.presentation.navigation.courses.navigateToCourses
import com.culture.estet.ui.presentation.navigation.feed.navigateToFeed
import com.culture.estet.ui.presentation.navigation.map.navigateToMap
import com.culture.estet.ui.presentation.navigation.tasks.navigateToTasks
import com.culture.estet.ui.presentation.navigation.tournament.navigateToTournament


@Composable
fun rememberAppScreenState(
    navHostController: NavHostController = rememberNavController()
): AppScreenState {
    return remember(navHostController) {
        AppScreenState(navHostController)
    }
}

@Stable
class AppScreenState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    var shouldShowBottomBar: MutableState<Boolean> = mutableStateOf(false)
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.route}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.TASK -> {
                    navController.navigateToTasks(topLevelNavOptions)
                }
                TopLevelDestination.TOURNAMENT -> {
                    navController.navigateToTournament(topLevelNavOptions)
                }
                TopLevelDestination.MAP -> {
                    navController.navigateToMap(topLevelNavOptions)
                }
                TopLevelDestination.COURSES -> {
                    navController.navigateToCourses(topLevelNavOptions)
                }
                TopLevelDestination.FEED -> {
                    navController.navigateToFeed(topLevelNavOptions)
                }
            }
        }
    }


    fun onBackClick() {
        navController.popBackStack()
    }


}

inline fun <T> trace(label: String, crossinline block: () -> T): T {
    try {
        Trace.beginSection(label)
        return block()
    } finally {
        Trace.endSection()
    }
}