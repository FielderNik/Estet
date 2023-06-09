package com.culture.estet.ui.presentation.tasks.tasklevel

import com.culture.estet.core.funcional.onFailure
import com.culture.estet.core.funcional.onSuccess
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.repository.TaskRepository
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskLevelViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : BaseViewModel<TaskLevelScreenState, TaskLevelEffect, TaskLevelAction>(TaskLevelScreenState.Loading) {

    override fun sendAction(action: TaskLevelAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: TaskLevelScreenState, action: TaskLevelAction) {
        when(currentState) {
            is TaskLevelScreenState.Levels -> {
                handleActionsLevelState(currentState, action)
            }
            TaskLevelScreenState.Loading -> {
                handleActionLoadingState(currentState, action)
            }
        }
    }

    private suspend fun handleActionLoadingState(currentState: TaskLevelScreenState, action: TaskLevelAction) {
        when(action) {
            is TaskLevelAction.Initialize -> {
                loadLevelData(action.userId, action.artType)
            }
        }
    }

    private suspend fun loadLevelData(userId: String, artType: ArtType) {
        withIo {
            taskRepository.getLevelData(userId = userId, artType = artType)
        }
            .onFailure {
                //todo
            }
            .onSuccess { levels ->
                setState(TaskLevelScreenState.Levels(levels))
            }
    }

    private suspend fun handleActionsLevelState(currentState: TaskLevelScreenState.Levels, action: TaskLevelAction) {

    }
}