package com.culture.estet.ui.presentation.states

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
class AppTopBarState(
    val navController: NavHostController
) {
    var title: String by mutableStateOf("")
    var isShowBackNavigate by mutableStateOf(false)
    var isShowProfile by mutableStateOf(true)
    var isShowTopBar: Boolean by mutableStateOf(false)


    fun setupTopBar(
        title: String,
        isShowBackNavigate: Boolean = false
    ) {
        this.title = title
        this.isShowBackNavigate = isShowBackNavigate
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}

@Composable
fun rememberAppTopBarState(
    navController: NavHostController = rememberNavController()
) : AppTopBarState {
    return remember {
        AppTopBarState(navController)
    }
}