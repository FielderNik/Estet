package com.culture.estet.ui.presentation.tasks

import com.culture.estet.domain.models.tasks.Task


sealed class TasksScreenState {

    data class HasNotTasks(
        val userId: String? = null,
    ) : TasksScreenState()

    data class TasksInProgress(
        val userId: String? = null,
        val tasks: List<Task>? = null
    ) : TasksScreenState()
}

sealed class TasksEffect {

}


sealed class TasksAction {
    object Initialize : TasksAction()
}

