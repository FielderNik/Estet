package com.culture.estet.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class AppNavigatorImpl(
    override val navController: NavHostController
) : AppNavigator {

    override val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    override fun navigate(destination: AppNavigationDestination, route: String?) {
        navController.navigate(route ?: destination.route)
    }

    override fun onBackClick() {
        navController.popBackStack()
    }


}

@Composable
fun rememberAppNavigator(
    navHostController: NavHostController = rememberNavController()
): AppNavigator {
    return remember {
        AppNavigatorImpl(navHostController)
    }
}