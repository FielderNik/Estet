package com.culture.estet.ui.presentation.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.culture.estet.ui.presentation.elements.AppBottomBar
import com.culture.estet.ui.presentation.elements.AppTopBar
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.navigation.*
import com.culture.estet.ui.presentation.states.AppScreenState
import com.culture.estet.ui.presentation.states.AppTopBarState
import com.culture.estet.ui.presentation.states.rememberAppTopBarState
import com.culture.estet.ui.presentation.states.rememberAppScreenState
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    appTopBarState: AppTopBarState = rememberAppTopBarState(),
    appScreenState: AppScreenState = rememberAppScreenState()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle


    LaunchedEffect(Unit) {
        appScreenState.navController.currentBackStackEntryFlow.flowWithLifecycle(lifecycle).collect { navBackStackEntry ->
            if (navBackStackEntry.destination.route in TopLevelDestination.values().map { it.route }) {
                appScreenState.shouldShowBottomBar.value = true
            }
        }
    }

    CompositionLocalProvider(
        LocalAppScreenState provides appScreenState,
        LocalAppTopBarState provides appTopBarState,
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                if (appTopBarState.isShowTopBar) {
                    AppTopBar(
                        title = appTopBarState.title,
                        isShowNavigateBack = appTopBarState.isShowBackNavigate
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(visible = appScreenState.shouldShowBottomBar.value) {
                    AppBottomBar(
                        destinations = appScreenState.topLevelDestinations,
                        onNavigateToDestination = appScreenState::navigateToTopLevelDestination,
                        currentDestination = appScreenState.currentDestination,
                    )
                }
            }
        ) { paddings ->

            AppNavHost(
                navHostController = appScreenState.navController,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddings)
            )
        }

    }
}
