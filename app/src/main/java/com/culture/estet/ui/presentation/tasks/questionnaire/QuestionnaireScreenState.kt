package com.culture.estet.ui.presentation.tasks.questionnaire

import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tasks.TaskGoalType
import com.culture.estet.domain.models.tasks.TaskLevelType

data class QuestionnaireScreenState(
    val userId: String? = null,
    val art: TaskArtType? = null,
    val level: TaskLevelType? = null,
    val goals: Set<TaskGoalType> = mutableSetOf(),
    val canStartTask: Boolean = false,
)

sealed class QuestionnaireEffect {
    data class StartTask(val userId: String, val artType: TaskArtType, val levelType: TaskLevelType) : QuestionnaireEffect()
}

sealed class QuestionnaireAction {
    data class Initialize(val userId: String) : QuestionnaireAction()
    data class SelectArtType(val artType: TaskArtType) : QuestionnaireAction()
    data class SelectLevelType(val levelType: TaskLevelType) : QuestionnaireAction()
    data class SelectGoalType(val goalType: TaskGoalType) : QuestionnaireAction()
    object CheckParametersAndStartTask : QuestionnaireAction()
}