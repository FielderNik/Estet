package com.culture.estet.data.map.remote

import javax.inject.Inject

class RetrofitSchoolsRemoteDataSource @Inject constructor(
    private val api: SchoolsApi
) : SchoolsRemoteDataSource {
    override suspend fun getSchools(): List<SchoolResponse> = api.getAllSchools()

    override suspend fun getSchool(id: String): SchoolResponse = api.getSchool(id)
}