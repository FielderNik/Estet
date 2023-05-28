package com.culture.estet.ui.presentation.navigation.tasks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination
import com.culture.estet.ui.presentation.tasks.TasksScreen

object TasksDestination : AppNavigationDestination {
    override val route: String = "tasks_route"
    override val destination: String = "tasks_destination"

    fun navigationRoute(): String {
        return route
    }
}

fun NavGraphBuilder.createTasksGraph() {
    composable(
        route = TasksDestination.route
    ) { backStackEntry ->

        TasksScreen()
    }
}

fun NavController.navigateToTasks(navOptions: NavOptions? = null) {
    this.navigate(TasksDestination.navigationRoute(), navOptions)
}