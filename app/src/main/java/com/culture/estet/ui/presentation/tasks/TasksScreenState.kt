package com.culture.estet.ui.presentation.tasks

import com.culture.estet.domain.models.tasks.TaskCategory


sealed class TasksScreenState {

    object Loading : TasksScreenState()

    data class TasksInProgress(
        val userId: String,
        val tasks: List<TaskCategory>,
    ) : TasksScreenState()
}

sealed class TasksEffect {

}


sealed class TasksAction {
    object Initialize : TasksAction()
}

