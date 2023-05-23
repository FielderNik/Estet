package com.culture.estet.ui.presentation.tasks.questions

import com.culture.estet.domain.models.questions.Answer
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.tasks.TasksArtType
import com.culture.estet.domain.models.tasks.TasksLevelType
import com.culture.estet.ui.presentation.tasks.questions.model.Step

data class QuestionsScreenState(
    val steps: List<Step> = emptyList()
)

sealed class QuestionsEffects {

}

sealed class QuestionsAction {
    data class Initialize(val userId: String, val artType: TasksArtType, val levelType: TasksLevelType) : QuestionsAction()
    data class SendAnswer(val answer: Answer, val question: Question) : QuestionsAction()
    object NextQuestion : QuestionsAction()
}