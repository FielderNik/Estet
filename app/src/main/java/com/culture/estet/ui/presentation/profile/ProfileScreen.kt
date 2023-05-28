package com.culture.estet.ui.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
    ProfileContent(state = state.value, viewModel::sendAction)
}

@Composable
private fun ProfileContent(
    state: ProfileScreenState,
    sendAction: (ProfileAction) -> Unit,
) {
    when (state) {
        ProfileScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ProfileScreenState.ProfileState -> {
            ProfileData(state, sendAction)
        }
    }
}

@Composable
private fun ProfileData(
    state: ProfileScreenState,
    sendAction: (ProfileAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(247.dp)
                .height(127.dp),
            painter = painterResource(R.drawable.moscow_art_schools_logo),
            contentDescription = null,
        )

        SubcomposeAsyncImage(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current).data("user.avatar").crossfade(true).build(),
            contentDescription = null,
            loading = {
                CircularProgressIndicator(trackColor = Color.Gray, strokeCap = StrokeCap.Round)
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
    }
}