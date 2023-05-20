package com.culture.estet.ui.presentation.events

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.ui.presentation.elements.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    viewModel: EventsViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendAction(EventsAction.Initialize)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(title = "ANDROID", false) //todo
        },
    ) { paddingValues ->

        EventsContent(
            modifier = Modifier.padding(paddingValues),
            events = state.value.events
        )

    }

}

@Composable
private fun EventsContent(
    modifier: Modifier,
    events: List<String>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),

    ) {
        items(items = events) {
            Text(text = it)
        }
    }
}
