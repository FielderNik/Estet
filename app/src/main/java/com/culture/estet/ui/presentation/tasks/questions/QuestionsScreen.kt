package com.culture.estet.ui.presentation.tasks.questions

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.domain.models.tasks.TasksArtType
import com.culture.estet.domain.models.tasks.TasksLevelType
import com.culture.estet.ui.presentation.elements.dialogs.ConfirmExitDialog
import com.culture.estet.ui.presentation.elements.dialogs.DialogButton
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalDialogEventBus
import com.culture.estet.ui.presentation.navigation.tasks.navigateToTasks
import com.culture.estet.ui.presentation.tasks.questions.model.Statistics
import com.culture.estet.ui.presentation.tasks.questions.model.Step
import com.culture.estet.ui.presentation.tasks.questions.model.StepType

@Composable
fun QuestionsScreen(
    userId: String,
    artType: TasksArtType,
    levelType: TasksLevelType,
    viewModel: QuestionsViewModel = hiltViewModel()
) {

    val step = viewModel.steps.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.sendAction(
            QuestionsAction.Initialize(
                userId = userId,
                artType = artType,
                levelType = levelType
            )
        )
    }


    StepContent(
        step = step.value,
        sendAction = viewModel::sendAction
    )
}

@Composable
private fun StepContent(
    step: Step?,
    sendAction: (QuestionsAction) -> Unit,
) {
    if (step == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HeaderBlock(statistics = step.statistics, sendAction = sendAction)
            Spacer(modifier = Modifier.height(24.dp))

            when (step.stepType) {
                StepType.QUESTION -> {
                    QuestionStep(step = step, sendAction = sendAction)
                }

                StepType.CORRECT_ANSWER -> {
                    CorrectAnswerStep(step = step, sendAction = sendAction)
                }

                StepType.ERROR_ANSWER -> {
                    IncorrectAnswerStep(step = step, sendAction = sendAction)
                }

                StepType.FINAL -> {
                    FinalScreen(step = step, sendAction = sendAction)
                }
            }
        }


    }

}

@Composable
private fun ColumnScope.QuestionStep(
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = step.question.question,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(36.dp))

    step.question.answers.forEach { answer ->
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onClick = {
                sendAction(QuestionsAction.SendAnswer(answer = answer, question = step.question))
            }
        ) {
            Text(text = answer.answer)
        }
    }
}

@Composable
private fun HeaderBlock(
    statistics: Statistics,
    sendAction: (QuestionsAction) -> Unit
) {
    val dialogEventBus = LocalDialogEventBus.current
    val navigator = LocalAppScreenState.current.navController

    Row(modifier = Modifier.fillMaxWidth()) {

        Text(
            modifier = Modifier.weight(1f),
            text = "Всего вопросов: ${statistics.questionsCount}  Правильных ответов: ${statistics.correctAnswerCount}"
        )
        IconButton(
            modifier = Modifier.size(48.dp),
            onClick = {
                dialogEventBus.showDialog {
                    ConfirmExitDialog(
                        title = stringResource(id = R.string.dialog_close_task_title),
                        message = stringResource(id = R.string.dialog_close_task_message),
                        question = stringResource(id = R.string.dialog_close_task_question),
                        cancelButton = DialogButton(
                            labelRes = R.string.action_stay,
                            onClick = {
                                dialogEventBus.hideDialog()
                            }
                        ),
                        confirmButton = DialogButton(
                            labelRes = R.string.action_close,
                            onClick = {
                                dialogEventBus.hideDialog()
                                navigator.navigateToTasks()
                            }
                        )
                    )
                }
            }
        ) {
            Icon(painter = painterResource(id = R.drawable.icon_close), contentDescription = null, tint = Color.Black)
        }
    }
}


@Composable
private fun ColumnScope.CorrectAnswerStep(
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = step.question.question,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(36.dp))

    step.question.answers.forEach { answer ->
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = false,
            onClick = {
//                    sendAction(QuestionsAction.SendAnswer(answer = answer, question = step.question))
            }
        ) {
            Text(text = answer.answer)
        }
    }

    Text(text = "Красавчик! Ты все разгадал!")
    TextButton(
        onClick = {
            sendAction(QuestionsAction.NextQuestion)
        }
    ) {
        Text(text = "Дальше")

    }
}

@Composable
private fun ColumnScope.FinalScreen(
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    Text(text = "Поздравляю, вы ответили на все вопросы!")
    Button(
        onClick = {

        }
    ) {
        Text(text = "Завершить")
    }
}


@Composable
private fun ColumnScope.IncorrectAnswerStep(
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = step.question.question,
        fontSize = 24.sp
    )
    Spacer(modifier = Modifier.height(36.dp))

    step.question.answers.forEach { answer ->
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = false,
            onClick = {
//                    sendAction(QuestionsAction.SendAnswer(answer = answer, question = step.question))
            }
        ) {
            Text(text = answer.answer)
        }
    }

    Text(text = step.question.description)
    TextButton(
        onClick = {
            sendAction(QuestionsAction.NextQuestion)
        }
    ) {
        Text(text = "Дальше")

    }
}