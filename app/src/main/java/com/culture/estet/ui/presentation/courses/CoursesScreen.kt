package com.culture.estet.ui.presentation.courses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState

@Composable
fun CoursesScreen(
    viewModel: CoursesViewModel = hiltViewModel()
) {
    val appTopBarState = LocalAppTopBarState.current
    val state = viewModel.state.collectAsState()
    val title = stringResource(id = R.string.title_screen_courses)

    LaunchedEffect(Unit) {
        appTopBarState.title = title
    }

    Box(modifier = Modifier.fillMaxSize().padding(24.dp), contentAlignment = Alignment.Center) {
        Text(text = "Данный функционал временно недоступен")
    }
}