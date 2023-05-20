package com.culture.estet.ui.presentation.navigation.courses

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.culture.estet.ui.presentation.courses.CoursesScreen
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination

object CoursesDestination : AppNavigationDestination {
    override val route: String = "courses_route"
    override val destination: String = "courses_destination"

    fun navigationRoute(): String {
        return route
    }
}


fun NavGraphBuilder.createCoursesGraph() {
    composable(
        route = CoursesDestination.route
    ) { backStackEntry ->

        CoursesScreen()
    }
}

fun NavController.navigateToCourses(navOptions: NavOptions? = null) {
    this.navigate(CoursesDestination.navigationRoute(), navOptions)
}