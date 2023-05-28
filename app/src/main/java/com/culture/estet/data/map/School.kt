package com.culture.estet.data.map

import com.yandex.mapkit.geometry.Point

data class School(
    val id: String,
    val point: Point,
    val name: String,
    val openingHours: String
)
