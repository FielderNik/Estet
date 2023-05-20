package com.culture.estet.ui.presentation.tasks

import com.culture.estet.domain.models.Question


sealed class TasksScreenState {

    data class QuestionnaireNotFilled(
        val userId: String? = null,
    ) : TasksScreenState()

    data class QuestionnaireFilled(
        val questions: List<Question>
    ) : TasksScreenState()
}

sealed class TasksEffect {

}


sealed class TasksAction {
    object Initialize : TasksAction()
}

