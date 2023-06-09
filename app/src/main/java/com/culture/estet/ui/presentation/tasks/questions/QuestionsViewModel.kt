package com.culture.estet.ui.presentation.tasks.questions

import android.util.Log
import com.culture.estet.core.funcional.onFailure
import com.culture.estet.core.funcional.onSuccess
import com.culture.estet.domain.models.questions.Answer
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tasks.TaskLevelType
import com.culture.estet.domain.repository.QuestionRepository
import com.culture.estet.domain.repository.StatisticsRepository
import com.culture.estet.ui.presentation.base.BaseViewModel
import com.culture.estet.ui.presentation.tasks.questions.model.Statistics
import com.culture.estet.ui.presentation.tasks.questions.model.Step
import com.culture.estet.ui.presentation.tasks.questions.model.StepType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.ArrayDeque
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val statisticsRepository: StatisticsRepository,
) : BaseViewModel<QuestionsScreenState, QuestionsEffects, QuestionsAction>(QuestionsScreenState()) {

    val steps: MutableSharedFlow<Step> = MutableSharedFlow()
    private val questionsQueue = ArrayDeque<Question>()
    private var currentStatistics: Statistics = Statistics(0,0, 0)
    private var userId: String? = null

    override fun sendAction(action: QuestionsAction) {
        launchOnMain {
            when(action) {
                is QuestionsAction.Initialize -> {
                    userId = action.userId
                    loadQuestions(action.userId, action.artType, action.levelType)
                }

                is QuestionsAction.SendAnswer -> {
                    checkAnswer(
                        selectedAnswer = action.answer,
                        currentQuestion = action.question
                    )
                }

                QuestionsAction.NextQuestion -> {
                    nextQuestion()
                }
                QuestionsAction.SaveResultsAndExit -> {
                    saveResult()
                    exitQuestions()
                }
            }
        }
    }

    private suspend fun exitQuestions() {
        sendEffect(QuestionsEffects.ExitQuestions)
    }

    private suspend fun saveResult() {

    }

    private fun fillStatistics() {
        val updatedStatistics = currentStatistics.copy(questionsCount = questionsQueue.size)
        currentStatistics = updatedStatistics
    }

    private suspend fun nextQuestion() {
        val question = next()
        val step = if (question == null) {
            Step(
                stepType = StepType.FINAL,
                question = Question.getEmptyQuestion(),
                isFinishStep = questionsQueue.isEmpty(),
                statistics = currentStatistics,
                selectedAnswer = null
            )
        } else {
            incrementCurrentQuestion()
            Step(
                stepType = StepType.QUESTION,
                question = question,
                isFinishStep = questionsQueue.isEmpty(),
                statistics = currentStatistics,
                selectedAnswer = null,
            )
        }
        steps.emit(step)

    }

    private suspend fun checkAnswer(selectedAnswer: Answer, currentQuestion: Question) {
        saveStatistics(selectedAnswer, currentQuestion)
    }

    private suspend fun saveStatistics(selectedAnswer: Answer, currentQuestion: Question) {
        userId?.also {

            statisticsRepository.saveStatistics(userId = it, questionId = currentQuestion.id, selectedAnswerId = selectedAnswer.id)
                .onFailure {
                }
                .onSuccess {

                    handleAnswer(selectedAnswer, currentQuestion)
                }
        }
    }

    private suspend fun handleAnswer(selectedAnswer: Answer, currentQuestion: Question) {
        val step = if (selectedAnswer.isCorrect) {
            incrementCorrectAnswer()
            Step(
                stepType = StepType.CORRECT_ANSWER,
                question = currentQuestion,
                isFinishStep = false,
                statistics = currentStatistics,
                selectedAnswer = selectedAnswer,
            )
        } else {
            Step(
                stepType = StepType.ERROR_ANSWER,
                question = currentQuestion,
                isFinishStep = false,
                statistics = currentStatistics,
                selectedAnswer = selectedAnswer,
            )
        }
        steps.emit(step)
    }

    private suspend fun loadQuestions(userId: String, artType: ArtType, levelType: TaskLevelType) {
        withIo {
            questionRepository.getQuestionsByParameters(userId = userId, artType = artType, levelType = levelType)
        }
            .onFailure {
                //todo обработка ошибки???
            }
            .onSuccess { questions ->
                val sortedQuestions = questions.sortedBy { it.ordinal }
                questionsQueue.addAll(sortedQuestions)

                fillStatistics()
                nextQuestion()
            }

    }

    private fun next(): Question? {
        return questionsQueue.pollFirst()
    }

    private fun incrementCorrectAnswer() {
        currentStatistics = currentStatistics.copy(
            correctAnswerCount = currentStatistics.correctAnswerCount + 1,
        )
    }

    private fun incrementCurrentQuestion() {
        currentStatistics = currentStatistics.copy(
            currentQuestionCount = currentStatistics.currentQuestionCount + 1
        )
    }


}



