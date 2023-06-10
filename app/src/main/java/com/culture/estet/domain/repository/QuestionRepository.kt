package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.mock.Questions
import com.culture.estet.data.tasks.questions.local.QuestionDao
import com.culture.estet.data.tasks.questions.local.QuestionEntity
import com.culture.estet.data.tasks.questions.remote.QuestionRemoteDataSource
import com.culture.estet.data.tasks.questions.remote.toQuestionListEntity
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tasks.TaskLevelType
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestionsByParameters(userId: String, artType: TaskArtType, levelType: TaskLevelType): Either<Failure, List<Question>>
    suspend fun updateAllQuestions(): Either<Failure, None>
    suspend fun getAllQuestions(): Either<Failure, List<QuestionEntity>>
}

class QuestionRepositoryImpl @Inject constructor(
    private val questionRemoteDataSource: QuestionRemoteDataSource,
    private val questionDao: QuestionDao,
): BaseRepository(), QuestionRepository {
    override suspend fun getQuestionsByParameters(userId: String, artType: TaskArtType, levelType: TaskLevelType): Either<Failure, List<Question>> {
        return handleRequest {
            when(levelType) {
                TaskLevelType.BEGINNER -> Questions.Lite.questionsLite
                TaskLevelType.ADVANCED -> Questions.Advanced.questionsAdvanced
                TaskLevelType.EXPERT -> Questions.Expert.questionsHard
            }
        }
    }

    override suspend fun updateAllQuestions(): Either<Failure, None> {
        return handleSuspendRequest {
            val questionResponse = questionRemoteDataSource.getAllQuestions()
            questionDao.upsertAllQuestions(questionResponse.toQuestionListEntity())
            None
        }
    }

    override suspend fun getAllQuestions(): Either<Failure, List<QuestionEntity>> {
        return handleSuspendRequest {
            questionDao.getAll()
        }
    }

}