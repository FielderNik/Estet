package com.culture.estet.data.map

import com.culture.estet.domain.models.ArtType

data class School(
    val id: String,
    val artType: ArtType,
    val geoPoint: GeoPoint,
    val name: String,
    val openingHours: String
)
