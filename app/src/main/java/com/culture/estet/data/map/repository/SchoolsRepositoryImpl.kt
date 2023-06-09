package com.culture.estet.data.map.repository

import com.culture.estet.core.funcional.onFailure
import com.culture.estet.core.funcional.onSuccess
import com.culture.estet.data.map.School
import com.culture.estet.data.map.local.SchoolsDao
import com.culture.estet.data.map.local.toSchoolsList
import com.culture.estet.data.map.mock.getMockSchools
import com.culture.estet.data.map.remote.SchoolResponse
import com.culture.estet.data.map.remote.SchoolsRemoteDataSource
import com.culture.estet.data.map.remote.toSchoolsList
import com.culture.estet.data.map.remote.toSchoolsListEntity
import com.culture.estet.domain.repository.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolsRepositoryImpl @Inject constructor(
    private val localDataSource: SchoolsDao,
    private val remoteDataSource: SchoolsRemoteDataSource,
): BaseRepository(), SchoolsRepository {
    override suspend fun getSchoolsList(): List<School> {
        val response = handleSuspendRequest {
            remoteDataSource.getSchools()
        }
        var data: List<School> = emptyList()
        response.onSuccess {
            data = it.toSchoolsList()
            saveSchools(it)
        }
        response.onFailure {
            val dbSchools = localDataSource.getAll()
            dbSchools?.let { data = it.toSchoolsList() }
        }
        return data
    }

    override suspend fun getSchool(): School {
        return getMockSchools()[0]
    }

    private suspend fun saveSchools(schoolsResponse: List<SchoolResponse>) {
        localDataSource.upsertAllSchools(schoolsResponse.toSchoolsListEntity())
    }
}