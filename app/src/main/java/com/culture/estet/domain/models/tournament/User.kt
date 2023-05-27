package com.culture.estet.domain.models.tournament

data class User(
    val id: String,
    val name: String,
    val avatar: String,
    val totalScore: Int,
    val scoreArt: ArtScore?,
)

