package com.culture.estet.data.tasks.statistics.remote

import com.culture.estet.core.network.EstetApi
import javax.inject.Inject

interface StatisticsRemoteDataSource {
    suspend fun getAllStatistics(userId: String): List<StatisticsResponse>
}

class StatisticsRemoteDataSourceImpl @Inject constructor(
    private val api: EstetApi,
): StatisticsRemoteDataSource {
    override suspend fun getAllStatistics(userId: String): List<StatisticsResponse> {
        return api.getStatistics(userId)
    }

}