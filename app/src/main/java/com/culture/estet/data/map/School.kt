package com.culture.estet.data.map

data class School(
    val id: String,
    val geoPoint: GeoPoint,
    val name: String,
    val openingHours: String
)
