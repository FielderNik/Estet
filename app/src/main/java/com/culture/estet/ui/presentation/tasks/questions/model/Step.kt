package com.culture.estet.ui.presentation.tasks.questions.model

import com.culture.estet.domain.models.questions.Question

data class Step(
    val stepType: StepType,
    val question: Question,
    val isFinishStep: Boolean,
    val statistics: Statistics,
)
