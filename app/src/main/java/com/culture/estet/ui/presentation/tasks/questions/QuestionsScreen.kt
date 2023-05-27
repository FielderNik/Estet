package com.culture.estet.ui.presentation.tasks.questions

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.culture.estet.R
import com.culture.estet.core.utils.scoreText
import com.culture.estet.domain.models.questions.Answer
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tasks.TaskLevelType
import com.culture.estet.ui.presentation.elements.ArtImage
import com.culture.estet.ui.presentation.elements.dialogs.ConfirmExitDialog
import com.culture.estet.ui.presentation.elements.dialogs.DialogButton
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalBottomSheetEventBus
import com.culture.estet.ui.presentation.localcomposition.LocalDialogEventBus
import com.culture.estet.ui.presentation.navigation.tasks.TasksDestination
import com.culture.estet.ui.presentation.navigation.tasks.navigateToTasks
import com.culture.estet.ui.presentation.tasks.questions.model.Statistics
import com.culture.estet.ui.presentation.tasks.questions.model.Step
import com.culture.estet.ui.presentation.tasks.questions.model.StepType
import com.culture.estet.ui.theme.LightPastelPurple

@Composable
fun QuestionsScreen(
    userId: String,
    artType: TaskArtType,
    levelType: TaskLevelType,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val navigator = LocalAppScreenState.current.navController
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

    LaunchedEffect(Unit) {
        viewModel.effect.flowWithLifecycle(lifecycle).collect {
            handleEffects(
                effect = it,
                navigator = navigator
            )
        }
    }


    StepContent(
        artType = artType,
        step = step.value,
        sendAction = viewModel::sendAction
    )
}

@Composable
private fun StepContent(
    artType: TaskArtType,
    step: Step?,
    sendAction: (QuestionsAction) -> Unit,
) {
    if (step == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        StepData(
            artType = artType,
            step = step,
            sendAction = sendAction
        )
    }

}

@Composable
private fun StepData(
    artType: TaskArtType,
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
    ) {
        HeaderBlock(statistics = step.statistics, sendAction = sendAction)
        if (step.stepType != StepType.FINAL) {
            QuestionBlock(artType, step.question.question)
        }

        when (step.stepType) {
            StepType.QUESTION -> {
                QuestionStep(
                    step = step,
                    sendAction = sendAction
                )
            }

            StepType.CORRECT_ANSWER -> {
                CorrectAnswerStep(
                    step = step,
                    sendAction = sendAction
                )
            }

            StepType.ERROR_ANSWER -> {
                IncorrectAnswerStep(
                    step = step,
                    sendAction = sendAction
                )
            }

            StepType.FINAL -> {
                FinalScreen(
                    artType = artType,
                    step = step,
                    sendAction = sendAction
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.QuestionStep(
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {

    step.question.answers.forEachIndexed { index, answer ->  
        AnswerBlock(
            isAnswerStep = step.stepType in arrayOf(StepType.ERROR_ANSWER, StepType.CORRECT_ANSWER),
            answer = answer,
            question = step.question,
            sendAction = sendAction,
            selectedAnswer = step.selectedAnswer
        )
        if (index < step.question.answers.size - 1) {
            Spacer(modifier = Modifier.height(24.dp))
        }
        
    }
}

@Composable
private fun AnswerBlock(
    isAnswerStep: Boolean,
    answer: Answer,
    selectedAnswer: Answer?,
    question: Question,
    sendAction: (QuestionsAction) -> Unit,
) {

    val borderColor = when {
        !isAnswerStep -> Color.Gray
        answer.isCorrect -> Color.Green
        selectedAnswer == answer && !answer.isCorrect -> Color.Red
        else -> Color.Gray
    }
    OutlinedButton(
        modifier = Modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(33),
        border = BorderStroke(2.dp, borderColor),
        enabled = !isAnswerStep,
        onClick = {
            sendAction(QuestionsAction.SendAnswer(answer = answer, question = question))
        }
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = answer.answer,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
private fun QuestionBlock(
    artType: TaskArtType,
    question: String,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        ArtImage(
            size = 160.dp, artType = artType
        )
        Text(text = question, fontSize = 24.sp)
    }
}

@Composable
private fun HeaderBlock(
    statistics: Statistics,
    sendAction: (QuestionsAction) -> Unit
) {
    val progressAnimationDuration = 1500
    val dialogEventBus = LocalDialogEventBus.current
    val navigator = LocalAppScreenState.current.navController
    val progress = if (statistics.questionsCount > 0) {
        statistics.currentQuestionCount.toFloat() / statistics.questionsCount.toFloat()
    } else {
        0f
    }
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimationDuration, easing = FastOutSlowInEasing)
    )
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

        LinearProgressIndicator(
            modifier = Modifier
                .height(16.dp)
                .weight(1f),
            progress = progressAnimation,
            strokeCap = StrokeCap.Round,
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
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.Green, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.icon_info), contentDescription = null, tint = Color.Black)
            }
        }
    }
}


@Composable
private fun ColumnScope.CorrectAnswerStep(
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    step.question.answers.forEachIndexed { index, answer ->
        AnswerBlock(
            isAnswerStep = step.stepType in arrayOf(StepType.ERROR_ANSWER, StepType.CORRECT_ANSWER),
            answer = answer,
            question = step.question,
            sendAction = sendAction,
            selectedAnswer = step.selectedAnswer
        )
        if (index < step.question.answers.size - 1) {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    AnswerBottomBlock(
        step = step,
        isCorrect = true,
        sendAction = sendAction
    )

}

@Composable
private fun AnswerBottomBlock(
    step: Step,
    isCorrect: Boolean,
    sendAction: (QuestionsAction) -> Unit,
) {
    val bottomSheetEventBus = LocalBottomSheetEventBus.current
    var isShowAnswer by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(isShowAnswer) {
        bottomSheetEventBus.show {
            CorrectAnswerContent(
                correctAnswer = step.question.answers.first { it.isCorrect },
                description = step.question.description,
                isCorrect = isCorrect,
                score = step.question.score,
                sendAction = sendAction,
                hideBottomSheet = {
                    bottomSheetEventBus.hide()
                }
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    isShowAnswer = !isShowAnswer
                }
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.Green), contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_question),
                    contentDescription = null
                )
            }
            Text(text = stringResource(id = R.string.title_right_answer))
        }

        TextButton(
            onClick = {
                bottomSheetEventBus.hide()
                sendAction(QuestionsAction.NextQuestion)
            }
        ) {
            Text(text = stringResource(id = R.string.action_next))
        }
    }
}

@Composable
private fun ColumnScope.FinalScreen(
    artType: TaskArtType,
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    
    val scoreAnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 24.sp)) {
            append(stringResource(id = R.string.title_you_collect_some_score))
        }

        withStyle(style = SpanStyle(fontSize = 120.sp, color = LightPastelPurple, fontWeight = FontWeight.Black, )) {
            append("${step.statistics.currentQuestionCount}")
        }

        withStyle(style = SpanStyle(fontSize = 24.sp)) {
            append(stringResource(id = scoreText(step.statistics.currentQuestionCount)))
        }
    }
    Text(text = stringResource(id = R.string.title_congratulation), fontSize = 32.sp, lineHeight = 36.sp)
    Text(text = stringResource(id = R.string.title_task_finished), fontSize = 32.sp, lineHeight = 36.sp)


    Text(text = scoreAnnotatedString)

    ArtImage(
        modifier = Modifier.offset(x = (-72).dp),
        size = 240.dp,
        artType = artType
    )
    Spacer(modifier = Modifier.weight(1f))
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = {
            sendAction(QuestionsAction.SaveResultsAndExit)
        }
    ) {
        Text(text = stringResource(id = R.string.action_finish))
    }
}


@Composable
private fun ColumnScope.IncorrectAnswerStep(
    step: Step,
    sendAction: (QuestionsAction) -> Unit,
) {
    step.question.answers.forEachIndexed { index, answer ->
        AnswerBlock(
            isAnswerStep = step.stepType in arrayOf(StepType.ERROR_ANSWER, StepType.CORRECT_ANSWER),
            answer = answer,
            question = step.question,
            sendAction = sendAction,
            selectedAnswer = step.selectedAnswer
        )
        if (index < step.question.answers.size - 1) {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    AnswerBottomBlock(
        step = step,
        isCorrect = false,
        sendAction = sendAction
    )

}


@Composable
private fun CorrectAnswerContent(
    correctAnswer: Answer,
    description: String,
    isCorrect: Boolean,
    score: Int,
    hideBottomSheet: () -> Unit,
    sendAction: (QuestionsAction) -> Unit,
) {

    Column() {
        Text(
            modifier = Modifier.fillMaxWidth(), 
            text = stringResource(id = R.string.title_correct_answer), 
            fontSize = 24.sp, 
            fontWeight = FontWeight.Bold, 
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = correctAnswer.answer.uppercase(),
            fontSize = 24.sp,
            color = Color.Green,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontSize = 16.sp,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isCorrect) {
                Text(text = "+ $score", color = Color.Green)
            } else {
                Box(modifier = Modifier.width(16.dp))
            }
            Button(
                onClick = {
                    hideBottomSheet()
                    sendAction(QuestionsAction.NextQuestion)
                }
            ) {
                Text(text = "Дальше")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


private suspend fun handleEffects(
    effect: QuestionsEffects,
    navigator: NavHostController
) {
    when(effect) {
        QuestionsEffects.ExitQuestions -> {
            val route = TasksDestination.navigationRoute()
            navigator.navigate(route)
        }
    }
}