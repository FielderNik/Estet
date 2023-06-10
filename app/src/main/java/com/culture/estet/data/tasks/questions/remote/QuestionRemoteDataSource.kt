package com.culture.estet.data.tasks.questions.remote

import com.culture.estet.core.network.EstetApi
import javax.inject.Inject


interface QuestionRemoteDataSource {
    suspend fun getAllQuestions(): List<QuestionResponse>
}

class QuestionRemoteDataSourceImpl @Inject constructor(
    private val api: EstetApi,
): QuestionRemoteDataSource {

    override suspend fun getAllQuestions(): List<QuestionResponse> {
        return api.getAllQuestions()
    }

}