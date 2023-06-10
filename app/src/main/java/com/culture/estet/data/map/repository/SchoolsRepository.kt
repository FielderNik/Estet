package com.culture.estet.data.map.repository

import com.culture.estet.data.map.School

interface SchoolsRepository {
    suspend fun getSchoolsList(): List<School>
    suspend fun getSchool(): School
}