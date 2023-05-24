package com.culture.estet.ui.presentation.navigation.tasks

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.culture.estet.domain.models.tasks.NavTasksArtType
import com.culture.estet.domain.models.tasks.NavTasksLevelType
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tasks.TaskLevelType
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination
import com.culture.estet.ui.presentation.tasks.questions.QuestionsScreen

object QuestionsDestination : AppNavigationDestination {
    const val USER_ID_ARG = "user_id_arg"
    const val ART_TYPE_ARG = "art_type_arg"
    const val LEVEL_TYPE_ARG = "level_type_arg"
    override val route: String = "questions_route?$USER_ID_ARG={$USER_ID_ARG}&$ART_TYPE_ARG={$ART_TYPE_ARG}&$LEVEL_TYPE_ARG={$LEVEL_TYPE_ARG}"

    override val destination: String = "questions_destination"

    fun navigationRoute(userId: String, artType: TaskArtType, levelType: TaskLevelType): String {
        val encodedUserId = Uri.encode(userId)
        return "questions_route?$USER_ID_ARG=$encodedUserId&$ART_TYPE_ARG=${artType.name}&$LEVEL_TYPE_ARG=${levelType.name}"
    }

    fun userId(entry: NavBackStackEntry): String {
        val encodedId = entry.arguments?.getString(USER_ID_ARG)
        return Uri.decode(encodedId)
    }

    fun artType(entry: NavBackStackEntry): TaskArtType {
        return entry.arguments?.getSerializable(ART_TYPE_ARG) as TaskArtType
    }

    fun levelType(entry: NavBackStackEntry): TaskLevelType {
        return entry.arguments?.getSerializable(LEVEL_TYPE_ARG) as TaskLevelType
    }
}

fun NavGraphBuilder.createQuestionsGraph() {
    composable(
        route = QuestionsDestination.route,
        arguments = listOf(
            navArgument(QuestionsDestination.USER_ID_ARG) {
                type = NavType.StringType
            },
            navArgument(QuestionsDestination.ART_TYPE_ARG) {
                type = NavTasksArtType()
            },
            navArgument(QuestionsDestination.LEVEL_TYPE_ARG) {
                type = NavTasksLevelType()
            },
        )

    ) { navBackStackEntry ->

        val userId = QuestionsDestination.userId(navBackStackEntry)
        val artType = QuestionsDestination.artType(navBackStackEntry)
        val levelType = QuestionsDestination.levelType(navBackStackEntry)

        QuestionsScreen(
            userId = userId,
            artType = artType,
            levelType = levelType,
        )
    }
}