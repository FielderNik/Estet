package com.culture.estet.ui.presentation.tasks.questions

import com.culture.estet.domain.models.questions.Answer
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tasks.TaskLevelType
import com.culture.estet.ui.presentation.tasks.questions.model.Step

data class QuestionsScreenState(
    val steps: List<Step> = emptyList()
)

sealed class QuestionsEffects {

}

sealed class QuestionsAction {
    data class Initialize(val userId: String, val artType: TaskArtType, val levelType: TaskLevelType) : QuestionsAction()
    data class SendAnswer(val answer: Answer, val question: Question) : QuestionsAction()
    object NextQuestion : QuestionsAction()
}