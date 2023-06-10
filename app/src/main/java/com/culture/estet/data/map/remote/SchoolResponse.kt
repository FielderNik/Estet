package com.culture.estet.data.map.remote

import com.culture.estet.data.map.GeoPoint
import com.culture.estet.data.map.School
import com.culture.estet.data.map.local.SchoolEntity
import com.culture.estet.domain.models.ArtType
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SchoolResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("art_type")
    val artType: Int,

    @SerializedName("point")
    val geoPoint: GeoPoint,

    @SerializedName("title")
    val name: String,

    @SerializedName("opening_hours")
    val openingHours: String
) : Serializable

fun SchoolResponse.toSchoolEntity(): SchoolEntity {
    return SchoolEntity(
        id = id,
        artType = ArtType.getById(artType).name.lowercase(),
        geoPoint = geoPoint,
        name = name,
        openingHours = openingHours
    )
}

fun SchoolResponse.toSchool(): School {
    return School(
        id = id,
        artType = ArtType.getById(artType),
        geoPoint = geoPoint,
        name = name,
        openingHours = openingHours
    )
}

fun List<SchoolResponse>.toSchoolsListEntity() = map(SchoolResponse::toSchoolEntity)
fun List<SchoolResponse>.toSchoolsList() = map(SchoolResponse::toSchool)