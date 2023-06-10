package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.tasks.statistics.local.StatisticsDao
import com.culture.estet.data.tasks.statistics.local.StatisticsEntity
import com.culture.estet.data.tasks.statistics.remote.StatisticsRemoteDataSource
import com.culture.estet.data.tasks.statistics.remote.toStatisticsListEntity
import javax.inject.Inject

interface StatisticsRepository {
    suspend fun updateStatistics(userId: String): Either<Failure, None>
    suspend fun getAllStatistics(userId: String): Either<Failure, List<StatisticsEntity>>
    suspend fun saveStatistics(userId: String, questionId: String, selectedAnswerId: String): Either<Failure, None>
}

class StatisticsRepositoryImpl @Inject constructor(
    private val statisticsDao: StatisticsDao,
    private val statisticsRemoteDataSource: StatisticsRemoteDataSource,
): BaseRepository(), StatisticsRepository {

    override suspend fun updateStatistics(userId: String): Either<Failure, None> {
        return handleSuspendRequest {
            val statistics = statisticsRemoteDataSource.getAllStatistics(userId)
            statisticsDao.upsertAllStatistics(statistics.toStatisticsListEntity())
            None
        }
    }

    override suspend fun getAllStatistics(userId: String): Either<Failure, List<StatisticsEntity>> {
        return handleSuspendRequest {
            statisticsDao.getAllByUserId(userId)
        }
    }

    override suspend fun saveStatistics(userId: String, questionId: String, selectedAnswerId: String): Either<Failure, None> {
        return handleSuspendRequest {
             val response = statisticsRemoteDataSource.saveStatistics(
                userId = userId,
                questionId = questionId,
                selectedAnswerId = selectedAnswerId
            )
            if (response != null) {
                statisticsDao.upsertStatistics(
                    StatisticsEntity(
                        id = response,
                        userId = userId,
                        questionId = questionId,
                        selectedAnswerId = selectedAnswerId
                    )
                )
                None
            } else {
                throw Exception("statistics not saves")
            }
        }

    }

}