package com.culture.estet.ui.presentation.navigation.tasks

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination
import com.culture.estet.ui.presentation.tasks.questionnaire.QuestionnaireScreen

object QuestionnaireDestination : AppNavigationDestination {
    const val USER_ID_ARG = "user_id_arg"
    override val route: String = "questionnaire_route/{$USER_ID_ARG}"
    override val destination: String = "questionnaire_destination"

    fun navigationRoute(userId: String): String {
        val encodedUserId = Uri.encode(userId)
        return "questionnaire_route/$encodedUserId"
    }

    fun userId(entry: NavBackStackEntry): String {
        val encodedId = entry.arguments?.getString(USER_ID_ARG)
        return Uri.decode(encodedId)
    }
}

fun NavGraphBuilder.createQuestionnaireGraph() {
    composable(
        route = QuestionnaireDestination.route,
        arguments = listOf(
            navArgument(QuestionnaireDestination.USER_ID_ARG) {type = NavType.StringType }
        )

    ) { navBackStackEntry ->

        val userId = QuestionnaireDestination.userId(navBackStackEntry)

        QuestionnaireScreen(
            userId = userId
        )
    }
}