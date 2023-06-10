package com.culture.estet.ui.presentation.initial

import com.culture.estet.core.funcional.onFailure
import com.culture.estet.core.funcional.onSuccess
import com.culture.estet.domain.repository.AnswerRepository
import com.culture.estet.domain.repository.QuestionRepository
import com.culture.estet.domain.repository.StatisticsRepository
import com.culture.estet.domain.repository.UserRepository
import com.culture.estet.domain.repository.settings.AppSettingsRepository
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository,
    private val userRepository: UserRepository,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
    private val statisticsRepository: StatisticsRepository,
) : BaseViewModel<InitialScreenState, InitialEffect, InitialAction>(InitialScreenState) {

    override fun sendAction(action: InitialAction) {
        launchOnMain {
            when(action) {
                InitialAction.Initialize -> initialize(false)
            }
        }
    }

    private suspend fun initialize(isLocal: Boolean) {
        withIo {
            val userId = appSettingsRepository.getUserId()
            if (userId.isNullOrEmpty()) {
                createUser(isLocal)
            } else {
                loadUserData(userId)
                loadQuestions()
                loadAnswers()
                loadStatistics(userId)
            }
        }
    }

    private suspend fun createUser(isLocal: Boolean) {
        if (isLocal) {
            createLocalUser()
        } else {
            createRemoteUser()
        }
    }

    private suspend fun createRemoteUser() {
        withIo {
            userRepository.createUser()
                .onFailure {
                    createLocalUser()
                }
                .onSuccess {
                    saveUserToLocal(it)
                }
        }
    }

    private suspend fun saveUserToLocal(userId: String) {
        appSettingsRepository.saveUserId(userId)
    }

    private suspend fun createLocalUser() {
        val generatedId = generateId()
        appSettingsRepository.saveUserId(generatedId)
    }

    private suspend fun loadUserData(userId: String) {
        userRepository.loadUserData(userId)
            .onFailure {
                //todo
            }
            .onSuccess {
                //todo
            }
        sendEffect(InitialEffect.Loaded)

    }

    private suspend fun loadQuestions() {
        questionRepository.updateAllQuestions()
            .onFailure {

            }
            .onSuccess {
                 getAllQuestions()
            }
    }

    private suspend fun getAllQuestions() {
        questionRepository.getAllQuestions()
            .onFailure {

            }
            .onSuccess {

            }
    }
    
    private suspend fun loadAnswers() {
        answerRepository.updateAnswers()
            .onFailure {

            }
            .onSuccess { 
                getAllAnswers()
            }
    }
    
    private suspend fun getAllAnswers() {
        answerRepository.getAllAnswers()
            .onFailure {

            }
            .onSuccess {

            }
    }

    private suspend fun loadStatistics(userId: String) {
        statisticsRepository.updateStatistics(userId)
            .onFailure {

            }
            .onSuccess {
                getStatistics(userId)

            }
    }

    private suspend fun getStatistics(userId: String) {
        statisticsRepository.getAllStatistics(userId)
            .onFailure {

            }
            .onSuccess {

            }
    }


    private fun generateId(): String {
        return UUID.randomUUID().toString()
    }


}