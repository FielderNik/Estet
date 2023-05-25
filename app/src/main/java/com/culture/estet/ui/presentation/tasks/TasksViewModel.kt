package com.culture.estet.ui.presentation.tasks

import com.culture.estet.domain.models.tasks.Task
import com.culture.estet.domain.models.tasks.TasksArtType
import com.culture.estet.domain.models.tasks.TasksLevelType
import com.culture.estet.core.generateId
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(

) : BaseViewModel<TasksScreenState, TasksEffect, TasksAction>(TasksScreenState.TasksInProgress()) {

    override fun sendAction(action: TasksAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: TasksScreenState, action: TasksAction) {
        when(currentState) {
            is TasksScreenState.HasNotTasks -> {
                handleActionNotTasksState(currentState, action)
            }
            is TasksScreenState.TasksInProgress -> {
                handleActionTasksInProgressState(currentState, action)
            }
        }
    }

    private suspend fun handleActionTasksInProgressState(currentState: TasksScreenState.TasksInProgress, action: TasksAction) {
        when(action) {
            TasksAction.Initialize -> {
                val user = getCurrentUser()
                val updatedState = currentState.copy(userId = user, tasks = list)
                setState(updatedState)
            }
        }
    }

    private suspend fun handleActionNotTasksState(currentState: TasksScreenState.HasNotTasks, action: TasksAction) {
        when(action) {
            TasksAction.Initialize -> {
                val user = getCurrentUser()
                setUserForNotFilledState(currentState = currentState, userId = user)
            }
        }
    }


    private suspend fun getCurrentUser(): String {
        return "user_id_1"
    }

    private suspend fun setUserForNotFilledState(currentState: TasksScreenState.HasNotTasks, userId: String) {
        val updatedState = currentState.copy(userId = userId)
        setState(updatedState)
    }

    private suspend fun setUserForTaskInProgressState(currentState: TasksScreenState.HasNotTasks, userId: String) {
        val updatedState = currentState.copy(userId = userId)
        setState(updatedState)
    }
}


val list = listOf(
        Task(
            id = generateId(),
            type = TasksArtType.MUSIC,
            level = TasksLevelType.BEGINNER,
            progress = 0.6f,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.THEATRE,
            level = TasksLevelType.BEGINNER,
            progress = 0.6f,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.DANCE,
            level = TasksLevelType.BEGINNER,
            progress = 0.8f,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.THEATRE,
            level = TasksLevelType.EXPERT,
            progress = 0.1f,
            questionsCount = 6,
            answerCount = 1,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.MUSIC,
            level = TasksLevelType.ADVANCED,
            progress = 1f,
            questionsCount = 6,
            answerCount = 6,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.DANCE,
            level = TasksLevelType.EXPERT,
            progress = 0.2f,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.MUSIC,
            level = TasksLevelType.BEGINNER,
            progress = 0.6f,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.THEATRE,
            level = TasksLevelType.BEGINNER,
            progress = 0.6f,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.DANCE,
            level = TasksLevelType.BEGINNER,
            progress = 0.8f,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = TasksArtType.THEATRE,
            level = TasksLevelType.EXPERT,
            progress = 0.1f,
            questionsCount = 6,
            answerCount = 1,
        ),
    )
