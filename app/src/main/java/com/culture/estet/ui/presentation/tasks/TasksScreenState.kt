package com.culture.estet.ui.presentation.tasks

import com.culture.estet.domain.models.Question


sealed class TasksScreenState {

    object QuestionnaireNotFilled : TasksScreenState()

    data class QuestionnaireFilled(
        val questions: List<Question>
    )
}

