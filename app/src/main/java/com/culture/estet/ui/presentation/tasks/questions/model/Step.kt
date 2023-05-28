package com.culture.estet.ui.presentation.tasks.questions.model

import com.culture.estet.domain.models.questions.Answer
import com.culture.estet.domain.models.questions.Question

data class Step(
    val stepType: StepType,
    val question: Question,
    val selectedAnswer: Answer?,
    val isFinishStep: Boolean,
    val statistics: Statistics,
)
