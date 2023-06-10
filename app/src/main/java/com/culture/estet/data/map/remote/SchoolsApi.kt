package com.culture.estet.data.map.remote

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.feed.BaseNews
import com.culture.estet.domain.models.profile.UserProfile
import retrofit2.http.GET
import retrofit2.http.Path

interface SchoolsApi {

    @GET("/schools_all")
    suspend fun getAllSchools(): List<SchoolResponse>

    @GET("schools/{id}")
    suspend fun getSchool(
        @Path("id") id: String
    ): SchoolResponse
}