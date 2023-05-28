package com.culture.estet.ui.presentation.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.culture.estet.R
import com.culture.estet.core.utils.scoreText
import com.culture.estet.domain.models.tournament.User
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.tournament.model.TournamentFilter

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

    LaunchedEffect(Unit) {
        viewModel.sendAction(TournamentAction.Initialize(""))
    }

    TournamentContent(state = state.value, sendAction = viewModel::sendAction)


}

@Composable
private fun TournamentContent(
    state: TournamentScreenState,
    sendAction: (TournamentAction) -> Unit,
) {
    when (state) {
        TournamentScreenState.Loading -> {
            CircularProgressIndicator()
        }
        is TournamentScreenState.TournamentState -> {
            TournamentData(state, sendAction)
        }
    }
}


@Composable
private fun TournamentData(
    state: TournamentScreenState.TournamentState,
    sendAction: (TournamentAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item() {
            FilterBlock(selectedFilter = state.selectedFilter, sendAction = sendAction)
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(items = state.users, key = { user -> user.id }) { user ->
            UserItem(filter = state.selectedFilter, user = user, sendAction = sendAction)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun FilterBlock(
    selectedFilter: TournamentFilter,
    sendAction: (TournamentAction) -> Unit,
) {
    val filters = TournamentFilter.values()
    FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)) {
        filters.forEachIndexed { index, tournamentFilter ->
            FilterChip(
                selected = tournamentFilter == selectedFilter,
                onClick = {
                    sendAction(TournamentAction.ChangeFilter(tournamentFilter))
                },
                leadingIcon = if (selectedFilter == tournamentFilter) {
                    { Icon(painter = painterResource(id = R.drawable.icon_check), contentDescription = null) }
                } else {
                    null
                },
                label = {
                    Text(text = stringResource(id = tournamentFilter.labelSource()))
                }
            )
        }
    }
}

@Composable
private fun UserItem(
    filter: TournamentFilter,
    user: User,
    sendAction: (TournamentAction) -> Unit,
) {

    val score = if (filter == TournamentFilter.ALL) {
        user.totalScore
    } else {
        val artType = filter.getArtTypeByFilter()
        if (user.scoreArt?.artType == artType) {
            user.scoreArt?.score ?: 0
        } else {
            0
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current).data(user.avatar).crossfade(true).build(),
            contentDescription = null,
            loading = {
                CircularProgressIndicator(color = Color.Gray, strokeCap = StrokeCap.Round, strokeWidth = 2.dp)
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray), contentAlignment = Alignment.Center
                ) {
                    Icon(painter = painterResource(id = R.drawable.icon_profile), contentDescription = null, tint = Color.White)
                }
            },
            contentScale = ContentScale.Crop,
        )

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopStart) {
            Text(text = user.name)
        }


        Column(modifier = Modifier.widthIn(min = 64.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "$score")
            Text(text = stringResource(id = scoreText(score)))
        }

    }
}

