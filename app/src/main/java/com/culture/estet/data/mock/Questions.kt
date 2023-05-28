package com.culture.estet.data.mock

import com.culture.estet.core.generateId
import com.culture.estet.domain.models.questions.Answer
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.questions.QuestionType

object Questions {

    object Lite {
        private val answerLite11 = Answer(
            id = generateId(),
            answer = "дирижёр",
            isCorrect = false
        )

        private val answerLite12 = Answer(
            id = generateId(),
            answer = "композитор",
            isCorrect = true
        )

        private val answerLite13 = Answer(
            id = generateId(),
            answer = "концертмейстер",
            isCorrect = false
        )

        private val answerLite14 = Answer(
            id = generateId(),
            answer = "вокалист",
            isCorrect = false
        )

        private val questionLite1 = Question(
            id = generateId(),
            ordinal = 1,
            question = "Кто сочиняет музыку?",
            type = QuestionType.EASY,
            answers = listOf(answerLite11, answerLite12, answerLite13, answerLite14),
            score = 1,
            description = "Композитор – автор, создатель музыкальных произведений"
        )


        private val answerLite21 = Answer(
            id = generateId(),
            answer = "Санкт-Петербург",
            isCorrect = false
        )

        private val answerLite22 = Answer(
            id = generateId(),
            answer = "Екатеринбург",
            isCorrect = false
        )

        private val answerLite23 = Answer(
            id = generateId(),
            answer = "Москва",
            isCorrect = true
        )

        private val answerLite24 = Answer(
            id = generateId(),
            answer = "Нижний Новгород",
            isCorrect = false
        )

        private val questionLite2 = Question(
            id = generateId(),
            ordinal = 2,
            question = "В каком городе находится Государственный академический Большой театр России?",
            type = QuestionType.EASY,
            answers = listOf(answerLite21, answerLite22, answerLite23, answerLite24),
            score = 1,
            description = "Государственный академический Большой театр России расположен в Москве по адресу: Театральная площадь, д. 1."
        )



        private val answerLite31 = Answer(
            id = generateId(),
            answer = "танцуют",
            isCorrect = false
        )

        private val answerLite32 = Answer(
            id = generateId(),
            answer = "поют",
            isCorrect = true
        )

        private val answerLite33 = Answer(
            id = generateId(),
            answer = "дирижируют",
            isCorrect = false
        )

        private val answerLite34 = Answer(
            id = generateId(),
            answer = "играют на струнных музыкальных инструментах",
            isCorrect = false
        )

        private val questionLite3 = Question(
            id = generateId(),
            ordinal = 3,
            question = "Чем занимаются участники хора?",
            type = QuestionType.EASY,
            answers = listOf(answerLite31, answerLite32, answerLite33, answerLite34),
            score = 1,
            description = "Хор – певческий коллектив, исполняющий вокальную музыку."
        )

        val questionsLite = listOf(questionLite1, questionLite2, questionLite3)
    }

    object Advanced {
        private val answerAdvanced11 = Answer(
            id = generateId(),
            answer = "баян",
            isCorrect = false
        )

        private val answerAdvanced12 = Answer(
            id = generateId(),
            answer = "бандонеон",
            isCorrect = false
        )

        private val answerAdvanced13 = Answer(
            id = generateId(),
            answer = "аккордеон",
            isCorrect = true
        )

        private val answerAdvanced14 = Answer(
            id = generateId(),
            answer = "гармонь \"Хромка\"",
            isCorrect = false
        )

        private val questionAdvanced1 = Question(
            id = generateId(),
            ordinal = 1,
            question = "Как называется инструмент, в котором одна из клавиатур напоминает клавиатуру фортепиано?",
            type = QuestionType.ADVANCED,
            answers = listOf(answerAdvanced11, answerAdvanced12, answerAdvanced13, answerAdvanced14),
            score = 1,
            description = "Аккордеон – музыкальный инструмент, в котором  правая клавиатура фортепианного типа, то есть, напоминает клавиатуру фортепиано."
        )


        private val answerAdvanced21 = Answer(
            id = generateId(),
            answer = "4",
            isCorrect = true
        )

        private val answerAdvanced22 = Answer(
            id = generateId(),
            answer = "5",
            isCorrect = false
        )

        private val answerAdvanced23 = Answer(
            id = generateId(),
            answer = "6",
            isCorrect = false
        )

        private val answerAdvanced24 = Answer(
            id = generateId(),
            answer = "8",
            isCorrect = false
        )

        private val questionAdvanced2 = Question(
            id = generateId(),
            ordinal = 2,
            question = "Сколько струн на виолончели?",
            type = QuestionType.ADVANCED,
            answers = listOf(answerAdvanced21, answerAdvanced22, answerAdvanced23, answerAdvanced24),
            score = 1,
            description = "Виолончель – струнный смычковый музыкальный инструмент, который имеет 4 струны."
        )




        private val answerAdvanced31 = Answer(
            id = generateId(),
            answer = "композитор",
            isCorrect = false
        )

        private val answerAdvanced32 = Answer(
            id = generateId(),
            answer = "трубач",
            isCorrect = false
        )

        private val answerAdvanced33 = Answer(
            id = generateId(),
            answer = "скрипач",
            isCorrect = false
        )

        private val answerAdvanced34 = Answer(
            id = generateId(),
            answer = "пианист",
            isCorrect = true
        )

        private val questionAdvanced3 = Question(
            id = generateId(),
            ordinal = 3,
            question = "Денис Мацуев – …",
            type = QuestionType.ADVANCED,
            answers = listOf(answerAdvanced31, answerAdvanced32, answerAdvanced33, answerAdvanced34),
            score = 1,
            description = "Денис Мацуев – российский пианист, Народный артист РФ, победитель XI Международного конкурса имени П.И. Чайковского."
        )

        val questionsAdvanced = listOf(questionAdvanced1, questionAdvanced2, questionAdvanced3)
    }

    object Expert {
        private val answerHard11 = Answer(
            id = generateId(),
            answer = "опера",
            isCorrect = false
        )

        private val answerHard12 = Answer(
            id = generateId(),
            answer = "балет",
            isCorrect = true
        )

        private val answerHard13 = Answer(
            id = generateId(),
            answer = "симфония",
            isCorrect = false
        )

        private val answerHard14 = Answer(
            id = generateId(),
            answer = "соната",
            isCorrect = false
        )

        private val questionHard1 = Question(
            id = generateId(),
            ordinal = 1,
            question = "Как называется музыкальный спектакль, содержание которого воплощается через музыку и танец?",
            type = QuestionType.HARD,
            answers = listOf(answerHard11, answerHard12, answerHard13, answerHard14),
            score = 1,
            description = "Балет – вид сценического искусства, содержание которого выражается в музыкально-хореографических образах."
        )



        private val answerHard21 = Answer(
            id = generateId(),
            answer = "прима",
            isCorrect = false
        )

        private val answerHard22 = Answer(
            id = generateId(),
            answer = "секунда",
            isCorrect = false
        )

        private val answerHard23 = Answer(
            id = generateId(),
            answer = "минута",
            isCorrect = true
        )

        private val answerHard24 = Answer(
            id = generateId(),
            answer = "октава",
            isCorrect = false
        )

        private val questionHard2 = Question(
            id = generateId(),
            ordinal = 2,
            question = "Какого музыкального интервала не существует?",
            type = QuestionType.HARD,
            answers = listOf(answerHard21, answerHard22, answerHard23, answerHard24),
            score = 1,
            description = "Музыкальный интервал – расстояние между двумя различными по высоте звуками. Прима, секунда, октава – музыкальные интервалы. Минута не является музыкальным интервалом, минута – единица измерения времени."
        )




        private val answerHard31 = Answer(
            id = generateId(),
            answer = "скрипка, контрабас, домра, арфа",
            isCorrect = true
        )

        private val answerHard32 = Answer(
            id = generateId(),
            answer = "балалайка, гитара, гобой, виолончель",
            isCorrect = false
        )

        private val answerHard33 = Answer(
            id = generateId(),
            answer = "контрабас, виолончель, тромбон, укулеле",
            isCorrect = false
        )

        private val answerHard34 = Answer(
            id = generateId(),
            answer = "арфа, скрипка, домра, кларнет",
            isCorrect = false
        )

        private val questionHard3 = Question(
            id = generateId(),
            ordinal = 3,
            question = "Выберите вариант ответа, где перечислены струнные музыкальные инструменты.",
            type = QuestionType.HARD,
            answers = listOf(answerHard31, answerHard32, answerHard33, answerHard34),
            score = 1,
            description = "Скрипка, контрабас, домра, арфа, балалайка, гитара, виолончель, укулеле – струнные музыкальные инструменты. Гобой, тромбон, кларнет – духовые музыкальные инструменты."
        )

        val questionsHard = listOf(questionHard1, questionHard2, questionHard3)
    }


}