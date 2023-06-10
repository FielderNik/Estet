package com.culture.estet.core.network

import com.culture.estet.data.tasks.answers.remote.AnswerResponse
import com.culture.estet.data.tasks.questions.remote.QuestionResponse
import com.culture.estet.data.tasks.statistics.remote.StatisticsIdResponse
import com.culture.estet.data.tasks.statistics.remote.StatisticsRequest
import com.culture.estet.data.tasks.statistics.remote.StatisticsResponse
import com.culture.estet.domain.models.profile.UserProfile
import com.culture.estet.domain.repository.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface EstetApi {

    @GET("users_all")
    suspend fun getAllUsers(): List<UserProfile>

    @GET("create_empty_user")
    suspend fun createEmptyUser(): UserResponse

    @GET("user/{id}")
    suspend fun getUserData(
        @Path("id") id: String
    ): UserProfile


    //questions

    @GET("questions_all")
    suspend fun getAllQuestions(): List<QuestionResponse>


    //answers

    @GET("answers_all")
    suspend fun getAllAnswers(): List<AnswerResponse>



    //statistics

    @GET("statistics/{user_id}")
    suspend fun getStatistics(
        @Path("user_id") userId: String
    ): List<StatisticsResponse>

    @POST("statistics")
    suspend fun saveStatistics(
        @Body statisticsRequest: StatisticsRequest
    ): StatisticsIdResponse


}