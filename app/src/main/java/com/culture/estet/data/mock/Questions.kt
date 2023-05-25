package com.culture.estet.data.mock

import com.culture.estet.core.generateId
import com.culture.estet.domain.models.questions.Answer
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.questions.QuestionType

object Questions {
    private val answer11 = Answer(
        id = generateId(),
        answer = "15",
        isCorrect = false
    )

    private val answer12 = Answer(
        id = generateId(),
        answer = "4",
        isCorrect = true
    )

    private val answer13 = Answer(
        id = generateId(),
        answer = "7",
        isCorrect = false
    )

    private val answer14 = Answer(
        id = generateId(),
        answer = "88",
        isCorrect = false
    )

    private val question1 = Question(
        id = generateId(),
        ordinal = 1,
        question = "Сколько будет 2*2",
        type = QuestionType.EASY,
        answers = listOf(answer11, answer12, answer13, answer14),
        score = 1,
        description = "Ты чего, не знаешь сколько будет 2 * 2? Да ты кукухой поехал!"
    )

    private val question2 = Question(
        id = generateId(),
        ordinal = 2,
        question = "Сколько будет 3*3",
        type = QuestionType.EASY,
        answers = listOf(answer11, answer12, answer13, answer14),
        score = 1,
        description = "Ты чего, не знаешь сколько будет 3 * 3? Да ты кукухой поехал!"
    )

    private val question3 = Question(
        id = generateId(),
        ordinal = 3,
        question = "Сколько будет 4*4",
        type = QuestionType.EASY,
        answers = listOf(answer11, answer12, answer13, answer14),
        score = 1,
        description = "Ты чего, не знаешь сколько будет 4 * 4? Да ты кукухой поехал!"
    )

    val questions = listOf(question1, question2, question3)


}