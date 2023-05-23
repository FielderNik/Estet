package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.mock.Questions
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.tasks.TasksArtType
import com.culture.estet.domain.models.tasks.TasksLevelType
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestionsByParameters(userId: String, artType: TasksArtType, levelType: TasksLevelType): Either<Failure, List<Question>>
}

class QuestionRepositoryImpl @Inject constructor(): BaseRepository(), QuestionRepository {
    override suspend fun getQuestionsByParameters(userId: String, artType: TasksArtType, levelType: TasksLevelType): Either<Failure, List<Question>> {
        return handleRequest {
            Questions.questions
        }
    }

}