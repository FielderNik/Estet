package com.culture.estet.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.culture.estet.ui.presentation.navigation.courses.createCoursesGraph
import com.culture.estet.ui.presentation.navigation.feed.createFeedGraph
import com.culture.estet.ui.presentation.navigation.map.createMapGraph
import com.culture.estet.ui.presentation.navigation.tasks.TasksDestination
import com.culture.estet.ui.presentation.navigation.tasks.createQuestionnaireGraph
import com.culture.estet.ui.presentation.navigation.tasks.createQuestionsGraph
import com.culture.estet.ui.presentation.navigation.tasks.createTasksGraph
import com.culture.estet.ui.presentation.navigation.tournament.createTournamentGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = TasksDestination.route
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        eventsGraph()
        createTasksGraph()
        createTournamentGraph()
        createMapGraph()
        createCoursesGraph()
        createFeedGraph()

        createQuestionnaireGraph()
        createQuestionsGraph()

    }
}