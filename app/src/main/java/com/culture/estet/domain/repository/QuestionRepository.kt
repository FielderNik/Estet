package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.tasks.answers.local.AnswerDao
import com.culture.estet.data.tasks.answers.local.toAnswerUiModelList
import com.culture.estet.data.tasks.questions.local.QuestionDao
import com.culture.estet.data.tasks.questions.local.QuestionEntity
import com.culture.estet.data.tasks.questions.remote.QuestionRemoteDataSource
import com.culture.estet.data.tasks.questions.remote.toQuestionListEntity
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.questions.Question
import com.culture.estet.domain.models.tasks.TaskLevelType
import javax.inject.Inject

interface QuestionRepository {
    suspend fun getQuestionsByParameters(userId: String, artType: ArtType, levelType: TaskLevelType): Either<Failure, List<Question>>
    suspend fun updateAllQuestions(): Either<Failure, None>
    suspend fun getAllQuestions(): Either<Failure, List<QuestionEntity>>
    suspend fun getQuestionsByArtType(artType: ArtType): Either<Failure, List<QuestionEntity>>
}

class QuestionRepositoryImpl @Inject constructor(
    private val questionRemoteDataSource: QuestionRemoteDataSource,
    private val questionDao: QuestionDao,
    private val answerDao: AnswerDao,
): BaseRepository(), QuestionRepository {
    override suspend fun getQuestionsByParameters(userId: String, artType: ArtType, levelType: TaskLevelType): Either<Failure, List<Question>> {
        return handleSuspendRequest {
            val questions = questionDao.getAllByParameters(/*artType.id*/0, levelType.id)
            questions.map { questionEntity ->
                Question(
                    id = questionEntity.id,
                    ordinal = 0,
                    question = questionEntity.question,
                    type = TaskLevelType.getById(questionEntity.artType),
                    answers = answerDao.getAllByQuestionId(questionId = questionEntity.id).toAnswerUiModelList(),
                    score = questionEntity.score,
                    description = questionEntity.description
                )
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

    override suspend fun getQuestionsByArtType(artType: ArtType): Either<Failure, List<QuestionEntity>> {
        return handleSuspendRequest {
            questionDao.getAllByArtType(0)
        }
    }

}