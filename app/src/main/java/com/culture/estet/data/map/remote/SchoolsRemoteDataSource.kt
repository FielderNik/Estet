package com.culture.estet.data.map.remote

interface SchoolsRemoteDataSource {
    suspend fun getSchools(): List<SchoolResponse>
    suspend fun getSchool(id: String): SchoolResponse
}