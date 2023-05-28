package com.culture.estet.ui.presentation.tasks.questionnaire

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.culture.estet.R
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tasks.TaskGoalType
import com.culture.estet.domain.models.tasks.TaskLevelType
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.navigation.tasks.QuestionsDestination

@Composable
fun QuestionnaireScreen(
    userId: String,
    viewModel: QuestionnaireViewModel = hiltViewModel()
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val appState = LocalAppScreenState.current
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        appState.shouldShowBottomBar.value = false
    }

    LaunchedEffect(Unit) {
        viewModel.sendAction(QuestionnaireAction.Initialize(userId))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.flowWithLifecycle(lifecycle).collect { effect ->
            handleEffects(effect, appState.navController)
        }
    }


    QuestionnaireScreenContent(
        userId = userId,
        state = state.value,
        sendAction = viewModel::sendAction
    )
}

private suspend fun handleEffects(effect: QuestionnaireEffect, navController: NavHostController) {
    when (effect) {
        is QuestionnaireEffect.StartTask -> {
            val route = QuestionsDestination.navigationRoute(userId = effect.userId, artType = effect.artType, levelType = effect.levelType)
            navController.navigate(route)
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun QuestionnaireScreenContent(
    userId: String,
    state: QuestionnaireScreenState,
    sendAction: (QuestionnaireAction) -> Unit
) {
    val pageCount = 3
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) { page ->
            when (page) {
                0 -> {
                    ArtType(
                        selectedArt = state.art,
                        sendAction = sendAction
                    )
                }

                1 -> {
                    LevelType(
                        selectedLevel = state.level,
                        sendAction = sendAction,
                    )
                }

                else -> {
                    GoalScreen(
                        selectedGoals = state.goals,
                        isStartButtonEnabled = state.canStartTask,
                        sendAction = sendAction,
                    )
                }
            }
        }
        Row(
            Modifier
                .height(32.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(20.dp)

                )
            }
        }
    }

}


@Composable
private fun ArtType(
    selectedArt: TaskArtType?,
    sendAction: (QuestionnaireAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 96.dp)
            .selectableGroup()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = stringResource(id = R.string.title_questionnaire_choice_art).uppercase(),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_art_theatre),
                iconId = R.drawable.image_theatre,
                isSelected = selectedArt == TaskArtType.THEATRE,
                selectableRole = Role.RadioButton,
                onClick = {
                    sendAction(QuestionnaireAction.SelectArtType(TaskArtType.THEATRE))
                }
            )
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_art_music),
                iconId = R.drawable.image_music,
                isSelected = selectedArt == TaskArtType.MUSIC,
                selectableRole = Role.RadioButton,
                onClick = {
                    sendAction(QuestionnaireAction.SelectArtType(TaskArtType.MUSIC))
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_art_dance),
                iconId = R.drawable.image_dance,
                isSelected = selectedArt == TaskArtType.DANCE,
                selectableRole = Role.RadioButton,
                onClick = {
                    sendAction(QuestionnaireAction.SelectArtType(TaskArtType.DANCE))
                }
            )
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_art_painting),
                iconId = R.drawable.image_painting,
                isSelected = selectedArt == TaskArtType.PAINTING,
                selectableRole = Role.RadioButton,
                onClick = {
                    sendAction(QuestionnaireAction.SelectArtType(TaskArtType.PAINTING))
                }
            )
        }

    }

}

@Composable
fun RowScope.ArtTypeItem(
    title: String,
    @DrawableRes iconId: Int,
    isSelected: Boolean,
    selectableRole: Role,
    onClick: () -> Unit,
) {
    val matrix = ColorMatrix().apply {
        this.setToSaturation(0f)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .clip(RoundedCornerShape(36.dp))
            .selectable(selected = isSelected, enabled = true, role = selectableRole, onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(iconId),
            contentDescription = null,
            colorFilter = if (!isSelected) ColorFilter.colorMatrix(matrix) else null
        )
        Text(text = title, fontSize = 16.sp)
    }
}

@Composable
fun RowScope.GoalTypeItem(
    title: String,
    @DrawableRes iconId: Int,
    isSelected: Boolean,
    selectableRole: Role,
    onClick: () -> Unit,
) {
    val matrix = ColorMatrix().apply {
        this.setToSaturation(0f)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .clip(RoundedCornerShape(36.dp))
            .selectable(selected = isSelected, enabled = true, role = selectableRole, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(iconId),
            contentDescription = null,
            colorFilter = if (!isSelected) ColorFilter.colorMatrix(matrix) else null
        )
        Text(
            modifier = Modifier.widthIn(max = 120.dp),
            color = if (isSelected) Color.Black else Color.Black.copy(alpha = 0.7f),
            text = title,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LevelType(
    selectedLevel: TaskLevelType?,
    sendAction: (QuestionnaireAction) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 96.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = stringResource(id = R.string.title_questionnaire_choice_level).uppercase(),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_level_beginner),
                iconId = R.drawable.image_beginner,
                isSelected = selectedLevel == TaskLevelType.BEGINNER,
                selectableRole = Role.RadioButton,
                onClick = {
                    sendAction(QuestionnaireAction.SelectLevelType(TaskLevelType.BEGINNER))
                }
            )
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_level_advanced),
                iconId = R.drawable.image_advanced,
                isSelected = selectedLevel == TaskLevelType.ADVANCED,
                selectableRole = Role.RadioButton,
                onClick = {
                    sendAction(QuestionnaireAction.SelectLevelType(TaskLevelType.ADVANCED))
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_level_expert),
                iconId = R.drawable.image_professional,
                isSelected = selectedLevel == TaskLevelType.EXPERT,
                selectableRole = Role.RadioButton,
                onClick = {
                    sendAction(QuestionnaireAction.SelectLevelType(TaskLevelType.EXPERT))
                }
            )

        }

    }

}

@Composable
private fun GoalScreen(
    selectedGoals: Set<TaskGoalType>,
    isStartButtonEnabled: Boolean,
    sendAction: (QuestionnaireAction) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 72.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = stringResource(id = R.string.title_questionnaire_choice_goal).uppercase(),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.Bottom
        ) {
            GoalTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_goal_just_like_this),
                iconId = R.drawable.image_violet_circle,
                isSelected = selectedGoals.contains(TaskGoalType.JUST_LIKE_THIS),
                selectableRole = Role.Checkbox,
                onClick = {
                    sendAction(QuestionnaireAction.SelectGoalType(TaskGoalType.JUST_LIKE_THIS))
                }
            )
            GoalTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_goal_go_to_school),
                iconId = R.drawable.image_yellow_circle,
                isSelected = selectedGoals.contains(TaskGoalType.GO_TO_SCHOOL),
                selectableRole = Role.Checkbox,
                onClick = {
                    sendAction(QuestionnaireAction.SelectGoalType(TaskGoalType.GO_TO_SCHOOL))
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GoalTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_goal_parent),
                iconId = R.drawable.image_green_circle,
                isSelected = selectedGoals.contains(TaskGoalType.PARENT),
                selectableRole = Role.Checkbox,
                onClick = {
                    sendAction(QuestionnaireAction.SelectGoalType(TaskGoalType.PARENT))
                }
            )
            GoalTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_goal_already_study),
                iconId = R.drawable.image_yellow_green_circle,
                isSelected = selectedGoals.contains(TaskGoalType.ALREADY_STUDY),
                selectableRole = Role.Checkbox,
                onClick = {
                    sendAction(QuestionnaireAction.SelectGoalType(TaskGoalType.ALREADY_STUDY))
                }
            )

        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GoalTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_goal_for_self_development),
                iconId = R.drawable.image_yellow_light_circle,
                isSelected = selectedGoals.contains(TaskGoalType.FOR_SELF_DEVELOPMENT),
                selectableRole = Role.Checkbox,
                onClick = {
                    sendAction(QuestionnaireAction.SelectGoalType(TaskGoalType.FOR_SELF_DEVELOPMENT))
                }
            )
        }

        Button(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .fillMaxWidth()
                .height(56.dp),
            enabled = isStartButtonEnabled,
            onClick = {
                sendAction(QuestionnaireAction.CheckParametersAndStartTask)
            }
        ) {
            Text(text = stringResource(id = R.string.action_start_task).uppercase())
        }

    }

}

