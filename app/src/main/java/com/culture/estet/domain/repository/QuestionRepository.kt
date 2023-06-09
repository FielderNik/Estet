package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.mock.Questions
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tasks.TaskLevelType
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestionsByParameters(userId: String, artType: ArtType, levelType: TaskLevelType): Either<Failure, List<Question>>
}

class QuestionRepositoryImpl @Inject constructor(): BaseRepository(), QuestionRepository {
    override suspend fun getQuestionsByParameters(userId: String, artType: ArtType, levelType: TaskLevelType): Either<Failure, List<Question>> {
        return handleRequest {
            when(levelType) {
                TaskLevelType.BEGINNER -> Questions.Lite.questionsLite
                TaskLevelType.ADVANCED -> Questions.Advanced.questionsAdvanced
                TaskLevelType.EXPERT -> Questions.Expert.questionsHard
            }
        }
    }

}