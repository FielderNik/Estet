package com.culture.estet.ui.presentation.tasks

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.domain.models.tasks.TaskCategory
import com.culture.estet.domain.models.ArtType
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.navigation.tasks.QuestionnaireDestination
import com.culture.estet.ui.presentation.navigation.tasks.TaskLevelDestination
import com.culture.estet.ui.theme.PrimaryGreen
import com.culture.estet.ui.theme.Purple500
import kotlinx.coroutines.launch

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    val appTopBarState = LocalAppTopBarState.current
    val state = viewModel.state.collectAsState()
    val title = stringResource(id = R.string.title_screen_tasks)

    LaunchedEffect(Unit) {
        viewModel.sendAction(TasksAction.Initialize)
    }

    LaunchedEffect(Unit) {
        appTopBarState.title = title
    }


    TasksScreenContent(state.value, viewModel::sendAction)
}

@Composable
private fun TasksScreenContent(
    state: TasksScreenState,
    sendAction: (TasksAction) -> Unit
) {
    when (state) {
        is TasksScreenState.TasksInProgress -> {
            TasksInProgressScreen(
                userId = state.userId,
                tasks = state.tasks,
                sendAction = sendAction
            )
        }

        TasksScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TasksInProgressScreen(
    userId: String,
    tasks: List<TaskCategory>,
    sendAction: (TasksAction) -> Unit,
) {
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        HorizontalPager(
            pageCount = tasks.size,
            state = pagerState,
        ) { page ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 36.dp, start = 16.dp, end = 16.dp, bottom = 48.dp),
                contentAlignment = Alignment.Center
            ) {
                TaskCategoryContent(
                    userId = userId,
                    taskCategory = tasks[page],
                    sendAction = sendAction,
                )
            }
        }

        Row(
            Modifier
                .height(32.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(tasks.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) Purple500 else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(12.dp)

                )
            }
        }
    }


}

@Composable
private fun TaskCategoryContent(
    userId: String,
    taskCategory: TaskCategory,
    sendAction: (TasksAction) -> Unit
) {
    val navigator = LocalAppScreenState.current.navController
    val progress by remember {
        mutableStateOf(
            if (taskCategory.amountLevels == 0) {
                0f
            } else {
                taskCategory.completedLevels.toFloat() / taskCategory.amountLevels.toFloat()
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(taskCategory.type.background(isSystemInDarkTheme()), shape = RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .clickable {
                navigator.navigate(TaskLevelDestination.navigationRoute(userId, taskCategory.type))
            }
            .padding(start = 24.dp, end = 24.dp, top = 40.dp, bottom = 40.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TaskCategoryImage(taskCategory.type)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = taskCategory.type.stringSource()),
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (taskCategory.amountArtScore > 0) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.title_you_have_amount_score, taskCategory.amountArtScore),
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.title_you_have_not_score),
                textAlign = TextAlign.Center
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp),
            strokeCap = StrokeCap.Round
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = stringResource(id = R.string.title_task_completed, taskCategory.completedLevels, taskCategory.amountLevels))


    }
}

@Composable
private fun TaskCategoryImage(artType: ArtType) {
    val iconId = artType.painterSource()
    Image(
        modifier = Modifier
            .size(180.dp),
        painter = painterResource(iconId),
        contentDescription = null,
    )
}


@Composable
private fun ProgressTask(
    questionsCount: Int,
    answerCount: Int,
    modifier: Modifier = Modifier
) {
    val progress: Float by remember {
        mutableStateOf((answerCount.toFloat() / questionsCount.toFloat()))
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart,
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth(progress)
                .height(20.dp)
                .background(PrimaryGreen, shape = RoundedCornerShape(10.dp))
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, end = 4.dp, bottom = 2.dp),
            text = stringResource(R.string.title_task_count_questions_completed, answerCount, questionsCount),
            fontSize = 10.sp,
            color = Color.White
        )

    }
}


@Composable
private fun HasNotTasksScreen(
    userId: String?,
    sendAction: (TasksAction) -> Unit,
) {
    val navigator = LocalAppScreenState.current.navController
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 36.dp, horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.title_questionnaire_not_filled).uppercase(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light
        )
        Button(
            modifier = Modifier
                .height(72.dp)
                .fillMaxWidth(),
            onClick = {
                userId?.also {
                    coroutineScope.launch {
                        val route = QuestionnaireDestination.navigationRoute(userId)
                        navigator.navigate(route)
                    }
                }
            }
        ) {
            Text(
                text = stringResource(id = R.string.action_fill_questionnaire),
                fontSize = 20.sp,

                )
        }
    }
}
