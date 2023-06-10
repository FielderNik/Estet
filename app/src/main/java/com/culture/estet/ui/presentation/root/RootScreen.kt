package com.culture.estet.ui.presentation.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.flowWithLifecycle
import com.culture.estet.core.eventbus.rememberBottomSheetEventBus
import com.culture.estet.core.eventbus.rememberDialogEventBus
import com.culture.estet.ui.presentation.elements.AppBottomBar
import com.culture.estet.ui.presentation.elements.AppTopBar
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.localcomposition.LocalBottomSheetEventBus
import com.culture.estet.ui.presentation.localcomposition.LocalDialogEventBus
import com.culture.estet.ui.presentation.navigation.*
import com.culture.estet.ui.presentation.states.AppScreenState
import com.culture.estet.ui.presentation.states.AppTopBarState
import com.culture.estet.ui.presentation.states.rememberAppScreenState
import com.culture.estet.ui.presentation.states.rememberAppTopBarState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    appTopBarState: AppTopBarState = rememberAppTopBarState(),
    appScreenState: AppScreenState = rememberAppScreenState()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val coroutineScope = rememberCoroutineScope()
    val dialogEventBus = rememberDialogEventBus(coroutineScope)
    val bottomSheetEventBus = rememberBottomSheetEventBus(coroutineScope)
    val sheetState = rememberModalBottomSheetState()

    val isShowDialog = remember {
        mutableStateOf(false)
    }

    val isShowBottomSheet = remember {
        mutableStateOf(false)
    }


    LaunchedEffect(Unit) {
        appScreenState.navController.currentBackStackEntryFlow.flowWithLifecycle(lifecycle).collect { navBackStackEntry ->
            if (navBackStackEntry.destination.route in TopLevelDestination.values().map { it.route }) {
                appScreenState.shouldShowBottomBar.value = true
                appTopBarState.isShowTopBar = true
                appTopBarState.isShowProfile = true
                appTopBarState.isShowBackNavigate = false
            }
        }
    }

    LaunchedEffect(Unit) {
        bottomSheetEventBus.bottomSheetState.flowWithLifecycle(lifecycle).collect {
            isShowBottomSheet.value = it
        }
    }

    LaunchedEffect(Unit) {
        dialogEventBus.dialogState.flowWithLifecycle(lifecycle).collect {
            isShowDialog.value = it
        }
    }

    CompositionLocalProvider(
        LocalAppScreenState provides appScreenState,
        LocalAppTopBarState provides appTopBarState,
        LocalDialogEventBus provides dialogEventBus,
        LocalBottomSheetEventBus provides bottomSheetEventBus,
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                AnimatedVisibility(visible = appTopBarState.isShowTopBar) {
                    AppTopBar(
                        title = appTopBarState.title,
                        isShowNavigateBack = appTopBarState.isShowBackNavigate,
                        isShowProfile = appTopBarState.isShowProfile
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

            if (isShowDialog.value) {

                Dialog(
                    onDismissRequest = {
                        coroutineScope.launch {
                            dialogEventBus.hideDialog()
                        }
                    },
                ) {
                    dialogEventBus.dialog.value?.invoke()
                }
            }

            if (isShowBottomSheet.value) {
                ModalBottomSheet(
                    onDismissRequest = {
                        coroutineScope.launch {
                            bottomSheetEventBus.hide()
                        }
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        bottomSheetEventBus.bottomSheetContent.value?.invoke()
                    }

                }
            }
        }

    }
}
