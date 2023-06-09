package com.culture.estet.ui.presentation.tasks.questionnaire

import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tasks.TaskGoalType
import com.culture.estet.domain.models.tasks.TaskLevelType
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionnaireViewModel @Inject constructor(

) : BaseViewModel<QuestionnaireScreenState, QuestionnaireEffect, QuestionnaireAction>(QuestionnaireScreenState()) {

    override fun sendAction(action: QuestionnaireAction) {
        launchOnMain {
            handleAction(state.value, action)
        }
    }

    private suspend fun handleAction(currentState: QuestionnaireScreenState, action: QuestionnaireAction) {
        when(action) {
            is QuestionnaireAction.SelectArtType -> {
                updateSelectedArts(currentState, action.artType)
            }
            is QuestionnaireAction.SelectGoalType -> {
                updateSelectedGoals(currentState, action.goalType)
            }
            is QuestionnaireAction.SelectLevelType -> {
                updateLevelType(currentState, action.levelType)
            }

            QuestionnaireAction.CheckParametersAndStartTask -> {
                checkParametersAndStartTask(currentState)
            }

            is QuestionnaireAction.Initialize -> {
                updateUserId(currentState, action.userId)
            }
        }
    }

    private suspend fun checkParametersAndStartTask(currentState: QuestionnaireScreenState) {
        val userId = currentState.userId
        val artType = currentState.art
        val levelType = currentState.level
        if (userId != null && artType != null && levelType != null) {
            sendEffect(QuestionnaireEffect.StartTask(userId, artType, levelType))
        } else {
            //todo обработать ошибку
        }
    }

    private suspend fun updateLevelType(currentState: QuestionnaireScreenState, levelType: TaskLevelType) {
        val updatedState = currentState.copy(level = levelType)
        checkCanStart(updatedState)

    }

    private suspend fun updateSelectedGoals(currentState: QuestionnaireScreenState, goalType: TaskGoalType) {
        val currentGoals = currentState.goals.toMutableSet()
        if (currentGoals.contains(goalType)) {
            currentGoals.remove(goalType)
        } else {
            currentGoals.add(goalType)
        }
        val updatedState = currentState.copy(goals = currentGoals)
        checkCanStart(updatedState)
    }

    private suspend fun updateSelectedArts(currentState: QuestionnaireScreenState, artType: ArtType) {
        val updatedState = currentState.copy(art = artType)
        checkCanStart(updatedState)
    }

    private suspend fun checkCanStart(currentState: QuestionnaireScreenState) {
        val canStart = currentState.art != null && currentState.level != null && currentState.goals.isNotEmpty()
        if (canStart != currentState.canStartTask) {
            val updatedState = currentState.copy(canStartTask = canStart)
            setState(updatedState)
        } else {
            setState(currentState)
        }
    }

    private suspend fun updateUserId(currentState: QuestionnaireScreenState, userId: String) {
        val updatedState = currentState.copy(userId = userId)
        setState(updatedState)
    }
}