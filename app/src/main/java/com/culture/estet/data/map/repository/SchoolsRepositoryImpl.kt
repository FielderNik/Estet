package com.culture.estet.data.map.repository

import com.culture.estet.data.map.School
import com.culture.estet.data.map.local.SchoolsDao
import com.culture.estet.data.map.mock.getMockSchools
import com.culture.estet.data.map.remote.SchoolsRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolsRepositoryImpl @Inject constructor(
    private val localDataSource: SchoolsDao,
    private val remoteDataSource: SchoolsRemoteDataSource,
): SchoolsRepository {
    override suspend fun getSchoolsList(): List<School> {
        return getMockSchools()
    }
}