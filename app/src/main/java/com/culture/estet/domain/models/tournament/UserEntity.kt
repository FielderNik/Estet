package com.culture.estet.domain.models.tournament

import com.culture.estet.domain.models.tasks.TaskArtType

data class UserEntity(
    val id: String,
    val name: String,
    val avatar: String,
    val totalScore: Int,
    val scoreArts: List<ArtScore>,
)

data class ArtScore(
    val artType: TaskArtType,
    val score: Int,
)


fun UserEntity.toUser(artType: TaskArtType?): User {
    return User(
        id = id,
        name = name,
        avatar = avatar,
        totalScore = totalScore,
        scoreArt = scoreArts.find { it.artType == artType }
    )
}
