package com.culture.estet.data.map.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.culture.estet.core.generateId
import com.culture.estet.data.map.School
import com.culture.estet.data.map.local.SchoolEntity.Companion.SCHOOLS_TABLE_NAME
import com.yandex.mapkit.geometry.Point

@Entity(tableName = SCHOOLS_TABLE_NAME)
data class SchoolEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = generateId(),

    @ColumnInfo(name = "point")
    val point: Point,

    @ColumnInfo(name = "information")
    val name: String,

    @ColumnInfo(name = "opening_hours")
    val openingHours: String

) {
    companion object {
        const val SCHOOLS_TABLE_NAME = "schools_entities_table"
    }
}

fun SchoolEntity.toSchool(): School {
    return School(
        id = id,
        point = point,
        name = name,
        openingHours = openingHours
    )
}

fun List<SchoolEntity>.toSchoolsList() = map(SchoolEntity::toSchool)