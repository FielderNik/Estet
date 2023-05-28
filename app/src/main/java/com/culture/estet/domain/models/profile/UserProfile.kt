package com.culture.estet.domain.models.profile

import com.culture.estet.domain.models.tournament.ArtScore

data class UserProfile(
    val id: String,
    val name: String,
    val avatar: String,
    val totalScore: Int,
    val scoreArts: List<ArtScore>,
    val phone: String,
    val age: Int,
    val gender: String,
    val email: String
)