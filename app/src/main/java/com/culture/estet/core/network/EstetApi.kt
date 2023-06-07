package com.culture.estet.core.network

import com.culture.estet.domain.models.profile.UserProfile
import retrofit2.http.GET
import retrofit2.http.Path


interface EstetApi {

    @GET("users_all")
    suspend fun getAllUsers(): List<UserProfile>

    @GET("create_empty_user")
    suspend fun createEmptyUser(): String

    @GET("user/{id}")
    suspend fun getUserData(
        @Path("id") id: String
    ): UserProfile
}