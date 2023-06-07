package com.culture.estet.domain.models.profile

import com.google.gson.annotations.SerializedName

data class UserProfile(
    val id: String,
    val name: String? = null,
    @SerializedName("avatar_url")
    val avatar: String? = null,
    val phone: String? = null,
    val age: Int? = null,
    val email: String? = null,
)