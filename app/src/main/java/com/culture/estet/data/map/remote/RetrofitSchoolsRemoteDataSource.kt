package com.culture.estet.data.map.remote

import javax.inject.Inject

class RetrofitSchoolsRemoteDataSource @Inject constructor(): SchoolsRemoteDataSource {
    override suspend fun getSchools(): List<SchoolsResponse> {
        TODO("Not yet implemented")
    }
}