package com.culture.estet.ui.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val appTopBarState = LocalAppTopBarState.current
    val appState = LocalAppScreenState.current
    val state = viewModel.state.collectAsState()
    val title = stringResource(id = R.string.title_screen_profile)

    LaunchedEffect(Unit) {
        appTopBarState.title = title
        appTopBarState.isShowBackNavigate = true
        appState.shouldShowBottomBar.value = false
    }

}