package com.culture.estet.ui.presentation.tasks

import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(

) : BaseViewModel<TasksScreenState, TasksEffect, TasksAction>(TasksScreenState.QuestionnaireNotFilled()) {

    override fun sendAction(action: TasksAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: TasksScreenState, action: TasksAction) {
        when(currentState) {
            is TasksScreenState.QuestionnaireNotFilled -> {
                handleActionNotFilledState(currentState, action)
            }
            is TasksScreenState.QuestionnaireFilled -> {
                handleActionFilledState(currentState, action)
            }
        }
    }

    private suspend fun handleActionFilledState(currentState: TasksScreenState.QuestionnaireFilled, action: TasksAction) {
        when(action) {
            TasksAction.Initialize -> {

            }
        }
    }

    private suspend fun handleActionNotFilledState(currentState: TasksScreenState.QuestionnaireNotFilled, action: TasksAction) {
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

    private suspend fun setUserForNotFilledState(currentState: TasksScreenState.QuestionnaireNotFilled, userId: String) {
        val updatedState = currentState.copy(userId = userId)
        setState(updatedState)
    }
}