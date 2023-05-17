package com.culture.estet.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController

interface AppNavigator {
    val navController: NavHostController

    @get:Composable
    val currentDestination: NavDestination?


    fun navigate(destination: AppNavigationDestination, route: String? = null)

    fun onBackClick()
}