package com.culture.estet.ui.presentation.tasks.tasklevel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tasks.TaskLevel
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.navigation.tasks.QuestionsDestination

@Composable
fun TaskLevelScreen(
    userId: String,
    artType: TaskArtType,
    viewModel: TaskLevelViewModel = hiltViewModel()
) {
    val appScreenState = LocalAppScreenState.current
    val state = viewModel.state.collectAsState()
    val appTopBarState = LocalAppTopBarState.current
    val title = stringResource(id = artType.stringSource())

    LaunchedEffect(Unit) {
        viewModel.sendAction(TaskLevelAction.Initialize(userId = userId, artType = artType))
    }

    LaunchedEffect(Unit) {
        appScreenState.shouldShowBottomBar.value = false
    }

    LaunchedEffect(Unit) {
        appTopBarState.isShowBackNavigate = true
        appTopBarState.isShowProfile = true
        appTopBarState.title = title
    }

    TaskLevelContent(userId = userId, state = state.value, sendAction = viewModel::sendAction)
}

@Composable
private fun TaskLevelContent(
    userId: String,
    state: TaskLevelScreenState,
    sendAction: (TaskLevelAction) -> Unit
) {
    when (state) {
        is TaskLevelScreenState.Levels -> {
            Levels(userId = userId, levels = state.levels, sendAction = sendAction)
        }

        TaskLevelScreenState.Loading -> {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun Levels(
    userId: String,
    levels: List<TaskLevel>,
    sendAction: (TaskLevelAction) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        levels.forEach { level ->
            LevelItem(userId = userId, level = level)
        }
    }
}

@Composable
private fun LevelItem(
    userId: String,
    level: TaskLevel
) {
    val navigator = LocalAppScreenState.current.navController
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(33))
            .clickable {
                val route = QuestionsDestination.navigationRoute(
                    userId = userId,
                    artType = level.taskArtType,
                    levelType = level.taskLevelType
                )
                navigator.navigate(route)
            }
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(40.dp))
        Box(
            modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(160.dp),
                painter = painterResource(id = level.taskLevelType.circleSourceByArtType(artType = level.taskArtType)),
                contentDescription = null,

            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = level.taskLevelType.stringSource()),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.title_level_tasks, level.amountQuestions),
                    color = Color.White
                )
            }
        }
        Text(
            text = "${level.completedQuestions}/${level.amountQuestions}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}