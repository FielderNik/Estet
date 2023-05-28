package com.culture.estet.ui.presentation.navigation.webview

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.culture.estet.ui.presentation.navigation.AppNavigationDestination
import com.culture.estet.ui.presentation.navigation.webview.WebViewDestination.URL_ID_ARG
import com.culture.estet.ui.presentation.webview.WebViewScreen

object WebViewDestination : AppNavigationDestination {
    const val URL_ID_ARG = "url_id_arg"
    override val route: String = "webview_route/{${URL_ID_ARG}}"
    override val destination: String = "webview_destination"

    fun navigationRoute(url: String): String {
        val encodedUrl = Uri.encode(url)
        return "webview_route/$encodedUrl"
    }

    fun url(entry: NavBackStackEntry): String {
        val encodedUrl = entry.arguments?.getString(URL_ID_ARG)
        return Uri.decode(encodedUrl)
    }
}

fun NavGraphBuilder.createWebViewGraph() {
    composable(
        route = WebViewDestination.route,
        arguments = listOf(
            navArgument(URL_ID_ARG) {type = NavType.StringType }
        )
    ) { backStackEntry ->

        val url = WebViewDestination.url(backStackEntry)

        WebViewScreen(url)
    }
}