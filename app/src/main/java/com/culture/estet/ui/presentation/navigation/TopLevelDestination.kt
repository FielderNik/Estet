package com.culture.estet.ui.presentation.navigation

import androidx.annotation.DrawableRes
import com.culture.estet.R
import com.culture.estet.ui.presentation.navigation.courses.CoursesDestination
import com.culture.estet.ui.presentation.navigation.feed.FeedDestination
import com.culture.estet.ui.presentation.navigation.map.MapDestination
import com.culture.estet.ui.presentation.navigation.tasks.TasksDestination
import com.culture.estet.ui.presentation.navigation.tournament.TournamentDestination

enum class TopLevelDestination(
    val route: String,
    val destination: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
) {
    TASK(
        route = TasksDestination.route,
        destination = TasksDestination.destination,
        selectedIcon = R.drawable.icon_tasks,
        unselectedIcon = R.drawable.icon_tasks,
    ),
    TOURNAMENT(
        route = TournamentDestination.route,
        destination = TournamentDestination.destination,
        selectedIcon = R.drawable.icon_tournament,
        unselectedIcon = R.drawable.icon_tournament,
    ),
    MAP(
        route = MapDestination.route,
        destination = MapDestination.destination,
        selectedIcon = R.drawable.icon_map,
        unselectedIcon = R.drawable.icon_map,
    ),
    COURSES(
        route = CoursesDestination.route,
        destination = CoursesDestination.destination,
        selectedIcon = R.drawable.icon_courses,
        unselectedIcon = R.drawable.icon_courses,
    ),
    FEED(
        route = FeedDestination.route,
        destination = FeedDestination.destination,
        selectedIcon = R.drawable.icon_feed,
        unselectedIcon = R.drawable.icon_feed,
    )
}