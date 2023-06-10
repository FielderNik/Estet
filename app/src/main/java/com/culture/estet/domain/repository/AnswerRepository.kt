package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.tasks.answers.local.AnswerDao
import com.culture.estet.data.tasks.answers.local.AnswerEntity
import com.culture.estet.data.tasks.answers.remote.AnswerRemoteDataSource
import com.culture.estet.data.tasks.answers.remote.toAnswerListEntity
import javax.inject.Inject

interface AnswerRepository {
    suspend fun updateAnswers(): Either<Failure, None>
    suspend fun getAllAnswers(): Either<Failure, List<AnswerEntity>>
}

class AnswerRepositoryImpl @Inject constructor(
    private val answerRemoteDataSource: AnswerRemoteDataSource,
    private val answerDao: AnswerDao,
) : BaseRepository(), AnswerRepository {

    override suspend fun updateAnswers(): Either<Failure, None> {
        return handleSuspendRequest {
            val answerResponse = answerRemoteDataSource.getAllAnswers()
            answerDao.upsertAllAnswers(answerResponse.toAnswerListEntity())
            None
        }
    }

    override suspend fun getAllAnswers(): Either<Failure, List<AnswerEntity>> {
        return handleSuspendRequest {
            answerDao.getAll()
        }
    }

}