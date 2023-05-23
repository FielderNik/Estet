package com.culture.estet.ui.presentation.tasks.questionnaire

import com.culture.estet.domain.models.tasks.TasksArtType
import com.culture.estet.domain.models.tasks.TasksGoalType
import com.culture.estet.domain.models.tasks.TasksLevelType

data class QuestionnaireScreenState(
    val userId: String? = null,
    val art: TasksArtType? = null,
    val level: TasksLevelType? = null,
    val goals: Set<TasksGoalType> = mutableSetOf(),
    val canStartTask: Boolean = false,
)

sealed class QuestionnaireEffect {
    data class StartTask(val userId: String, val artType: TasksArtType, val levelType: TasksLevelType) : QuestionnaireEffect()
}

sealed class QuestionnaireAction {
    data class Initialize(val userId: String) : QuestionnaireAction()
    data class SelectArtType(val artType: TasksArtType) : QuestionnaireAction()
    data class SelectLevelType(val levelType: TasksLevelType) : QuestionnaireAction()
    data class SelectGoalType(val goalType: TasksGoalType) : QuestionnaireAction()
    object CheckParametersAndStartTask : QuestionnaireAction()
}