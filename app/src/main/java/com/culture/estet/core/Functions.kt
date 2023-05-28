package com.culture.estet.core

import java.util.UUID

fun generateId(): String {
    return UUID.randomUUID().toString()
}

fun generateUserId(id: Int): String {
    return "user_id_2_$id"
}