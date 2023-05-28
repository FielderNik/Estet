package com.culture.estet.ui.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.culture.estet.R
import com.culture.estet.domain.models.profile.UserProfile
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.theme.PurpleWhite80

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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ProfileScreenState.ProfileState -> {
            ProfileData(state.user, sendAction)
        }
    }
}

@Composable
private fun ProfileData(
    user: UserProfile,
    sendAction: (ProfileAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
            model = ImageRequest.Builder(LocalContext.current).data(user.avatar).crossfade(true)
                .build(),
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
                    Icon(
                        painter = painterResource(id = R.drawable.icon_profile),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            contentScale = ContentScale.Crop,
        )
        Information(user)
    }
}

@Composable
private fun Information(
    user: UserProfile
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        InfoBlock(
            context.getString(R.string.name),
            user.name
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoBlock(
            context.getString(R.string.age),
            user.age.toString()
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoBlock(
            context.getString(R.string.gender),
            user.gender
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoBlock(
            context.getString(R.string.phone),
            user.phone
        )
        Spacer(modifier = Modifier.height(12.dp))
        InfoBlock(
            context.getString(R.string.email),
            user.email
        )

    }

}

@Composable
private fun InfoBlock(
    title: String,
    info: String
) {
    Text(text = title)
    Text(
        text = info,
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(color = PurpleWhite80, cornerRadius = CornerRadius(8.0f))
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        fontWeight = FontWeight.Bold
    )
}