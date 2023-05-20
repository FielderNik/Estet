package com.culture.estet.ui.presentation.tasks

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.navigation.AppNavigator
import com.culture.estet.ui.presentation.navigation.tasks.QuestionnaireDestination
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
        is TasksScreenState.QuestionnaireFilled -> {
            UsualTasksScreen()
        }

        is TasksScreenState.QuestionnaireNotFilled -> {
            QuestionnaireNotFilledScreen(
                userId = state.userId,
                sendAction = sendAction
            )
        }
    }
}


@Composable
private fun UsualTasksScreen() {

}


@Composable
private fun QuestionnaireNotFilledScreen(
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
