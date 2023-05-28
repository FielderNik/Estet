package com.culture.estet.core

import java.util.UUID

fun generateId(): String {
    return UUID.randomUUID().toString()
}

fun generateUserId(id: Int): String {
    return "user_id_2_$id"
}

fun generateBirthdayId(id: Int): String {
    return "birthday_id_1_$id"
}