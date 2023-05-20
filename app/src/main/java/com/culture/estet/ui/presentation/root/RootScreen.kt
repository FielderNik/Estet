package com.culture.estet.ui.presentation.root

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.culture.estet.ui.presentation.elements.AppBottomBar
import com.culture.estet.ui.presentation.elements.AppTopBar
import com.culture.estet.ui.presentation.navigation.*
import com.culture.estet.ui.presentation.states.AppScreenState
import com.culture.estet.ui.presentation.states.AppTopBarState
import com.culture.estet.ui.presentation.states.rememberAppTopBarState
import com.culture.estet.ui.presentation.states.rememberAppScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    appTopBarState: AppTopBarState = rememberAppTopBarState(),
    appScreenState: AppScreenState = rememberAppScreenState()
) {


    CompositionLocalProvider() {

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
                if (appScreenState.shouldShowBottomBar.value) {
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
