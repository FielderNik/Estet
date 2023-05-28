package com.culture.estet.data.map

import com.yandex.mapkit.geometry.Point

data class GeoPoint(
    val lat: Double,
    val lon: Double
)

fun Point.toGeoPoint(): GeoPoint {
    return GeoPoint(
        lat = latitude,
        lon = longitude,
    )
}

fun GeoPoint.toPoint(): Point {
    return Point (lat, lon)
}

