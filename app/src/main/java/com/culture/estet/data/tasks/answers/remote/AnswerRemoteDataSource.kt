package com.culture.estet.data.tasks.answers.remote

import com.culture.estet.core.network.EstetApi
import javax.inject.Inject

interface AnswerRemoteDataSource {
    suspend fun getAllAnswers(): List<AnswerResponse>
}

class AnswerRemoteDataSourceImpl @Inject constructor(private val api: EstetApi) : AnswerRemoteDataSource {
    override suspend fun getAllAnswers(): List<AnswerResponse> {
        return api.getAllAnswers()
    }

}