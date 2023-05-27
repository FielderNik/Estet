package com.culture.estet.ui.presentation.tournament

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState

@Composable
fun TournamentScreen(
    viewModel: TournamentViewModel = hiltViewModel()
) {

    val appTopBarState = LocalAppTopBarState.current
    val state = viewModel.state.collectAsState()
    val title = stringResource(id = R.string.title_screen_tournament)

    LaunchedEffect(Unit) {
        appTopBarState.title = title
    }
}