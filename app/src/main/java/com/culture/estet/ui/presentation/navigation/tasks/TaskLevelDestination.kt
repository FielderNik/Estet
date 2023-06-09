package com.culture.estet.ui.presentation.navigation.tasks

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.culture.estet.domain.models.NavTasksArtType
import com.culture.estet.domain.models.ArtType
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination
import com.culture.estet.ui.presentation.tasks.tasklevel.TaskLevelScreen

object TaskLevelDestination : AppNavigationDestination {
    const val USER_ID_ARG = "user_id_arg"
    const val ART_TYPE_ARG = "art_type_arg"
    override val route: String = "task_level_route?$USER_ID_ARG={$USER_ID_ARG}&$ART_TYPE_ARG={$ART_TYPE_ARG}"

    override val destination: String = "task_level_destination"

    fun navigationRoute(userId: String, artType: ArtType): String {
        val encodedUserId = Uri.encode(userId)
        return "task_level_route?$USER_ID_ARG=$encodedUserId&$ART_TYPE_ARG=${artType.name}"
    }

    fun userId(entry: NavBackStackEntry): String {
        val encodedId = entry.arguments?.getString(USER_ID_ARG)
        return Uri.decode(encodedId)
    }

    fun artType(entry: NavBackStackEntry): ArtType {
        return entry.arguments?.getSerializable(ART_TYPE_ARG) as ArtType
    }

}

fun NavGraphBuilder.createTaskLevelGraph() {
    composable(
        route = TaskLevelDestination.route,
        arguments = listOf(
            navArgument(TaskLevelDestination.USER_ID_ARG) {
                type = NavType.StringType
            },
            navArgument(TaskLevelDestination.ART_TYPE_ARG) {
                type = NavTasksArtType()
            },
        )

    ) { navBackStackEntry ->

        val userId = TaskLevelDestination.userId(navBackStackEntry)
        val artType = TaskLevelDestination.artType(navBackStackEntry)

        TaskLevelScreen(
            userId = userId,
            artType = artType,
        )
    }
}