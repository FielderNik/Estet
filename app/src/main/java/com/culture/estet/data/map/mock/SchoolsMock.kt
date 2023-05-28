package com.culture.estet.data.map.mock

import com.culture.estet.core.generateId
import com.culture.estet.data.map.GeoPoint
import com.culture.estet.data.map.School
import com.yandex.mapkit.geometry.Point

fun getMockSchools(): List<School> = listOf(
    School(
        generateId(),
        GeoPoint(55.818948, 37.628442),
        "ГБУДО г.Москвы \"ДШИ им. А.С.Даргомыжского\"",
        "понедельник - пятница с 9:00 до 20:30\n" +
                "суббота — с 8:00 до 20:30\n" +
                "воскресенье — выходной день",
    ),
    School(
        generateId(),
        GeoPoint(55.770735, 37.414966),
        "ГБУДО г.Москвы \"ДШИ им. И.С.Козловского\"",
        "понедельник — суббота с 10:00 до 20:00\n" +
                "воскресенье — выходной день",
    ),
    School(
        generateId(),
        GeoPoint(55.866212, 37.701771),
        "ГБУДО г.Москвы \"ДШИ им. С.И.Мамонтова\"",
        "понедельник — воскресенье с 9:00 до 20:00\n",
    ),
    School(
        generateId(),
        GeoPoint(55.647391, 37.660602),
        "ГБУДО г.Москвы \"ДШИ им. С.Т.Рихтера\"",
        "понедельник — суббота с 10:00 до 20:00\n" +
                "воскресенье — выходной день",
    ),
)