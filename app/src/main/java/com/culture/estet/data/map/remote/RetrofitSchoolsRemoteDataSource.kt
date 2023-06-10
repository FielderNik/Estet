package com.culture.estet.data.map.remote

import com.culture.estet.core.network.EstetApi
import javax.inject.Inject

class RetrofitSchoolsRemoteDataSource @Inject constructor(api: EstetApi): SchoolsRemoteDataSource {
    override suspend fun getSchools(): List<SchoolsResponse> {
        return emptyList()
    }
}