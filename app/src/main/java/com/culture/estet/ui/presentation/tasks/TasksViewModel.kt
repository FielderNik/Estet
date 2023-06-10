package com.culture.estet.ui.presentation.tasks

import com.culture.estet.core.funcional.onFailure
import com.culture.estet.core.funcional.onSuccess
import com.culture.estet.domain.models.tasks.Task
import com.culture.estet.core.generateId
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tasks.TaskLevelType
import com.culture.estet.domain.repository.TaskRepository
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : BaseViewModel<TasksScreenState, TasksEffect, TasksAction>(TasksScreenState.Loading) {

    override fun sendAction(action: TasksAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: TasksScreenState, action: TasksAction) {
        when(currentState) {
            is TasksScreenState.Loading -> {
                handleActionLoadingState(currentState, action)
            }
            is TasksScreenState.TasksInProgress -> {
                handleActionTasksInProgressState(currentState, action)
            }
        }
    }

    private suspend fun handleActionTasksInProgressState(currentState: TasksScreenState.TasksInProgress, action: TasksAction) {
        when(action) {
            TasksAction.Initialize -> {
                loadTasks()
//                val user = getCurrentUser()
//                val updatedState = currentState.copy(userId = user, tasks = list)
//                setState(updatedState)
            }
        }
    }

    private suspend fun handleActionLoadingState(currentState: TasksScreenState.Loading, action: TasksAction) {
        when(action) {
            TasksAction.Initialize -> {
                loadTasks()
            }
        }
    }

    private suspend fun loadTasks() {
        val user = getCurrentUser()
        withIo {
            taskRepository.getTaskCategoriesByUserId(user)
        }
            .onFailure {
                //todo
            }
            .onSuccess { taskCategories ->
                val sortedTaskCategories = taskCategories.sortedBy { it.ordinal }
                setState(TasksScreenState.TasksInProgress(userId = user, tasks = sortedTaskCategories))
            }
    }


    private suspend fun getCurrentUser(): String {
        return "user_id_1"
    }

}


val list = listOf(
        Task(
            id = generateId(),
            type = ArtType.MUSIC,
            level = TaskLevelType.BEGINNER,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = ArtType.THEATRE,
            level = TaskLevelType.BEGINNER,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = ArtType.DANCE,
            level = TaskLevelType.BEGINNER,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = ArtType.THEATRE,
            level = TaskLevelType.EXPERT,
            questionsCount = 6,
            answerCount = 1,
        ),
        Task(
            id = generateId(),
            type = ArtType.MUSIC,
            level = TaskLevelType.ADVANCED,
            questionsCount = 6,
            answerCount = 6,
        ),
        Task(
            id = generateId(),
            type = ArtType.DANCE,
            level = TaskLevelType.EXPERT,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = ArtType.MUSIC,
            level = TaskLevelType.BEGINNER,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = ArtType.THEATRE,
            level = TaskLevelType.BEGINNER,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = ArtType.DANCE,
            level = TaskLevelType.BEGINNER,
            questionsCount = 6,
            answerCount = 4,
        ),
        Task(
            id = generateId(),
            type = ArtType.THEATRE,
            level = TaskLevelType.EXPERT,
            questionsCount = 6,
            answerCount = 1,
        ),
    )
