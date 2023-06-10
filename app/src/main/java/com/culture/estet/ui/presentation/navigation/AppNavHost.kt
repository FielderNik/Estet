package com.culture.estet.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.culture.estet.ui.presentation.navigation.courses.createCoursesGraph
import com.culture.estet.ui.presentation.navigation.feed.createFeedGraph
import com.culture.estet.ui.presentation.navigation.initial.InitialDestination
import com.culture.estet.ui.presentation.navigation.initial.createInitialGraph
import com.culture.estet.ui.presentation.navigation.map.createMapGraph
import com.culture.estet.ui.presentation.navigation.profile.createProfileGraph
import com.culture.estet.ui.presentation.navigation.tasks.*
import com.culture.estet.ui.presentation.navigation.tournament.createTournamentGraph
import com.culture.estet.ui.presentation.navigation.webview.createWebViewGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = InitialDestination.route
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        createInitialGraph()
        eventsGraph()
        createTasksGraph()
        createTournamentGraph()
        createMapGraph()
        createCoursesGraph()
        createFeedGraph()
        createProfileGraph()

        createQuestionnaireGraph()
        createQuestionsGraph()
        createTaskLevelGraph()

        createWebViewGraph()

    }
}