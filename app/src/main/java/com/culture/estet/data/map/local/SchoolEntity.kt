package com.culture.estet.data.map.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.culture.estet.core.generateId
import com.culture.estet.data.map.GeoPoint
import com.culture.estet.data.map.School
import com.culture.estet.data.map.local.SchoolEntity.Companion.SCHOOLS_TABLE_NAME
import com.culture.estet.domain.models.ArtType
import java.util.Arrays

import java.util.stream.Collectors


@Entity(tableName = SCHOOLS_TABLE_NAME)
data class SchoolEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "art_type")
    val artType: String,

    @ColumnInfo(name = "point")
    @TypeConverters(PointConverter::class)
    val geoPoint: GeoPoint,

    @ColumnInfo(name = "information")
    val name: String,

    @ColumnInfo(name = "opening_hours")
    val openingHours: String

) {
    companion object {
        const val SCHOOLS_TABLE_NAME = "schools_entities_table"
    }
}

class PointConverter {
    @TypeConverter
    fun fromPoint(point: GeoPoint): String {
        return "${point.lat},${point.lon}"
    }

    @TypeConverter
    fun toPoint(data: String): GeoPoint {
        val (lat, lon) = data.split(",")
        return GeoPoint(lat.toDouble(), lon.toDouble())
    }
}

fun SchoolEntity.toSchool(): School {
    return School(
        id = id,
        artType = ArtType.valueOf(artType),
        geoPoint = geoPoint,
        name = name,
        openingHours = openingHours
    )
}

fun List<SchoolEntity>.toSchoolsList() = map(SchoolEntity::toSchool)