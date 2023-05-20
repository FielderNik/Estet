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
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.R
import com.culture.estet.domain.models.TasksLevelType

@Composable
fun QuestionnaireScreen(
    userId: String
) {
    val appState = LocalAppScreenState.current

    LaunchedEffect(Unit) {
        appState.shouldShowBottomBar.value = false
    }


    QuestionnaireScreenContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun QuestionnaireScreenContent() {
    val pageCount = 3
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        HorizontalPager(
            pageCount = pageCount,
            state = pagerState
        ) { page ->
            when (page) {
                0 -> {
                    ArtType()
                }
                1 -> {
                    LevelType()
                }

                else -> {
                    GoalScreen()
                }
            }
        }
        Row(
            Modifier
                .height(50.dp)
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
private fun ArtType() {
    val music = remember {
        mutableStateOf(false)
    }
    val theatre = remember {
        mutableStateOf(false)
    }
    val painting = remember {
        mutableStateOf(false)
    }
    val dance = remember {
        mutableStateOf(false)
    }
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
                isSelected = theatre.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    theatre.value = !theatre.value
                }
            )
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_art_music),
                iconId = R.drawable.image_music,
                isSelected = music.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    music.value = !music.value
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
                isSelected = dance.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    dance.value = !dance.value
                }
            )
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_art_painting),
                iconId = R.drawable.image_painting,
                isSelected = painting.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    painting.value = !painting.value
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
        Text(text= title, fontSize = 16.sp)
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
            text= title,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LevelType() {
    val selectedType = remember {
        mutableStateOf<TasksLevelType?>(null)
    }


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
                isSelected = selectedType.value == TasksLevelType.BEGINNER,
                selectableRole = Role.RadioButton,
                onClick = {
                    selectedType.value = TasksLevelType.BEGINNER
                }
            )
            ArtTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_level_advanced),
                iconId = R.drawable.image_advanced,
                isSelected = selectedType.value == TasksLevelType.ADVANCED,
                selectableRole = Role.RadioButton,
                onClick = {
                    selectedType.value = TasksLevelType.ADVANCED
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
                title = stringResource(id = R.string.title_questionnaire_choice_level_professional),
                iconId = R.drawable.image_professional,
                isSelected = selectedType.value == TasksLevelType.PROFESSIONAL,
                selectableRole = Role.RadioButton,
                onClick = {
                    selectedType.value = TasksLevelType.PROFESSIONAL
                }
            )

        }

    }

}

@Composable
private fun GoalScreen() {
    val justLikeThis = remember {
        mutableStateOf(false)
    }
    val goToSchool = remember {
        mutableStateOf(false)
    }
    val parent = remember {
        mutableStateOf(false)
    }
    val alreadyStudy = remember {
        mutableStateOf(false)
    }
    val forSelfDevelopment = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 96.dp)
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
                isSelected = justLikeThis.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    justLikeThis.value = !justLikeThis.value
                }
            )
            GoalTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_goal_go_to_school),
                iconId = R.drawable.image_yellow_circle,
                isSelected = goToSchool.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    goToSchool.value = !goToSchool.value
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
                isSelected = parent.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    parent.value = !parent.value
                }
            )
            GoalTypeItem(
                title = stringResource(id = R.string.title_questionnaire_choice_goal_already_study),
                iconId = R.drawable.image_yellow_green_circle,
                isSelected = alreadyStudy.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    alreadyStudy.value = !alreadyStudy.value
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
                iconId = R.drawable.image_light_yellow_circle,
                isSelected = forSelfDevelopment.value,
                selectableRole = Role.Checkbox,
                onClick = {
                    forSelfDevelopment.value = !forSelfDevelopment.value
                }
            )


        }

    }

}

