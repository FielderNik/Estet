package com.culture.estet.ui.presentation.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.domain.models.tasks.Task
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.navigation.tasks.QuestionnaireDestination
import com.culture.estet.ui.theme.PrimaryGreen
import com.culture.estet.ui.theme.PrimaryViolet
import kotlinx.coroutines.launch

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendAction(TasksAction.Initialize)
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
            TasksInProgressScreen(state.userId, state.tasks)
        }

        is TasksScreenState.HasNotTasks -> {
            HasNotTasksScreen(
                userId = state.userId,
                sendAction = sendAction
            )
        }
    }
}


@Composable
private fun TasksInProgressScreen(
    userId: String?,
    tasks: List<Task>?,
) {
    val coroutineScope = rememberCoroutineScope()
    val navigator = LocalAppScreenState.current.navController

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    userId?.also {
                        coroutineScope.launch {
                            val route = QuestionnaireDestination.navigationRoute(userId)
                            navigator.navigate(route)
                        }
                    }
                },
                containerColor = PrimaryViolet,
                contentColor = Color.White
            ) {
                Icon(painter = painterResource(id = R.drawable.icon_add), contentDescription = null)
            }
        }
    ) { paddingValues ->

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tasks?.also {
                items(tasks) { task ->
                    TaskBlock(task = task)
                }
            }
        }
    }

}

@Composable
private fun TaskBlock(task: Task) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(task.type.background(), shape = RoundedCornerShape(24.dp))
            .height(160.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 12.dp)
        ) {
            Image(
                painter = painterResource(id = task.type.painterSource()),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            ProgressTask(questionsCount = task.questionsCount, answerCount = task.answerCount)
            
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = task.type.stringSource()).uppercase(),
                maxLines = 1,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black.copy(alpha = 0.4f),
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(48.dp)
                .background(
                    color = PrimaryGreen,
                    shape = RoundedCornerShape(topEnd = 24.dp, bottomStart = 24.dp)
                ), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = task.level.iconSource()),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ProgressTask(
    questionsCount: Int,
    answerCount: Int,
    modifier: Modifier = Modifier
) {
    val progress: Float by remember {
        mutableStateOf((answerCount.toFloat()/questionsCount.toFloat()))
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
