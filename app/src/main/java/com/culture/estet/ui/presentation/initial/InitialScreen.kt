package com.culture.estet.ui.presentation.initial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.navigation.tasks.TasksDestination

@Composable
fun InitialScreen(
    viewModel: InitialViewModel = hiltViewModel()
) {
    val appTopBarState = LocalAppTopBarState.current
    val appScreenState = LocalAppScreenState.current


    LaunchedEffect(Unit) {
        appTopBarState.isShowTopBar = false
        appScreenState.shouldShowBottomBar.value = false
    }

    LaunchedEffect(Unit) {
        viewModel.sendAction(InitialAction.Initialize)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when(effect) {
                InitialEffect.Loaded -> {
                    appScreenState.navController.navigate(TasksDestination.navigationRoute())
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}