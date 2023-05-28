package com.culture.estet.data.map.remote

import com.culture.estet.core.generateId
import com.culture.estet.data.map.GeoPoint
import com.culture.estet.data.map.School
import com.culture.estet.data.map.local.SchoolEntity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SchoolsResponse(

    @SerializedName("point")
    val geoPoint: GeoPoint,

    @SerializedName("name")
    val name: String,

    @SerializedName("opening_hours")
    val openingHours: String
) : Serializable

fun SchoolsResponse.toSchoolEntity(): SchoolEntity {
    return SchoolEntity(
        id = generateId(),
        geoPoint = geoPoint,
        name = name,
        openingHours = openingHours
    )
}

fun SchoolsResponse.toSchool(): School {
    return School(
        id = generateId(),
        geoPoint = geoPoint,
        name = name,
        openingHours = openingHours
    )
}

fun List<SchoolsResponse>.toSchoolsListEntity() = map(SchoolsResponse::toSchoolEntity)
fun List<SchoolsResponse>.toSchoolsList() = map(SchoolsResponse::toSchool)