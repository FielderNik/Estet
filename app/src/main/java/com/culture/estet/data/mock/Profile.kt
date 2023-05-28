package com.culture.estet.data.mock

import com.culture.estet.core.generateUserId
import com.culture.estet.domain.models.profile.UserProfile
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tournament.ArtScore

object Profile {

    object User {
        private val musicScoreUser = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 11
        )
        private val theatreScoreUser = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 21
        )
        private val paintingScoreUser = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 0
        )
        private val danceScoreUser = ArtScore(
            artType = TaskArtType.DANCE,
            score = 0
        )
        private val artScoresUser = listOf(musicScoreUser, theatreScoreUser, paintingScoreUser, danceScoreUser)
        private val userId = generateUserId(3)
        val userProfile = UserProfile(
            id = userId,
            name = "Елена Зорина",
            avatar = "https://i.pravatar.cc/100?u=${userId}",
            totalScore = artScoresUser.sumOf { it.score },
            scoreArts = artScoresUser,
            phone = "+7(905)-823-33-22",
            age = 18,
            gender = "Жен",
            email = "katerina421@mail.ru"
        )
    }
}