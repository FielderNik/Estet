package com.culture.estet.data.mock

import com.culture.estet.core.generateId
import com.culture.estet.domain.models.tasks.TaskArtType
import com.culture.estet.domain.models.tournament.ArtScore
import com.culture.estet.domain.models.tournament.UserEntity

object Tournament {

    object User1 {
        private val musicScoreUser1 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 10
        )
        private val theatreScoreUser1 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 5
        )
        private val paintingScoreUser1 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 2
        )
        private val danceScoreUser1 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 7
        )
        private val artScoresUser1 = listOf(musicScoreUser1, theatreScoreUser1, paintingScoreUser1, danceScoreUser1)
        val userEntity1 = UserEntity(
            id = generateId(),
            name = "Иван Петров",
            avatar = "",
            totalScore = artScoresUser1.sumOf { it.score },
            scoreArts = artScoresUser1
        )
    }

    object User2 {
        private val musicScoreUser2 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 17
        )
        private val theatreScoreUser2 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 2
        )
        private val paintingScoreUser2 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 0
        )
        private val danceScoreUser2 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 10
        )
        private val artScoresUser2 = listOf(musicScoreUser2, theatreScoreUser2, paintingScoreUser2, danceScoreUser2)
        val userEntity2 = UserEntity(
            id = generateId(),
            name = "Петр Иванов",
            avatar = "",
            totalScore = artScoresUser2.sumOf { it.score },
            scoreArts = artScoresUser2
        )
    }

    object User3 {
        private val musicScoreUser3 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 11
        )
        private val theatreScoreUser3 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 21
        )
        private val paintingScoreUser3 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 0
        )
        private val danceScoreUser3 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 0
        )
        private val artScoresUser3 = listOf(musicScoreUser3, theatreScoreUser3, paintingScoreUser3, danceScoreUser3)
        val userEntity3 = UserEntity(
            id = generateId(),
            name = "Елена Зорина",
            avatar = "",
            totalScore = artScoresUser3.sumOf { it.score },
            scoreArts = artScoresUser3
        )
    }

    object User4 {
        private val musicScoreUser4 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 10
        )
        private val theatreScoreUser4 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 5
        )
        private val paintingScoreUser4 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 2
        )
        private val danceScoreUser4 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 7
        )
        private val artScoresUser4 = listOf(musicScoreUser4, theatreScoreUser4, paintingScoreUser4, danceScoreUser4)
        val userEntity4 = UserEntity(
            id = generateId(),
            name = "Анна Смирнова",
            avatar = "",
            totalScore = artScoresUser4.sumOf { it.score },
            scoreArts = artScoresUser4
        )
    }

    object User5 {
        private val musicScoreUser5 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 10
        )
        private val theatreScoreUser5 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 5
        )
        private val paintingScoreUser5 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 2
        )
        private val danceScoreUser5 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 7
        )
        private val artScoresUser5 = listOf(musicScoreUser5, theatreScoreUser5, paintingScoreUser5, danceScoreUser5)
        val userEntity5 = UserEntity(
            id = generateId(),
            name = "Екатерина Волкова",
            avatar = "",
            totalScore = artScoresUser5.sumOf { it.score },
            scoreArts = artScoresUser5
        )
    }

    object User6 {
        private val musicScoreUser6 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 0
        )
        private val theatreScoreUser6 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 0
        )
        private val paintingScoreUser6 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 2
        )
        private val danceScoreUser6 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 7
        )
        private val artScoresUser6 = listOf(musicScoreUser6, theatreScoreUser6, paintingScoreUser6, danceScoreUser6)
        val userEntity6 = UserEntity(
            id = generateId(),
            name = "Александр Соколов",
            avatar = "",
            totalScore = artScoresUser6.sumOf { it.score },
            scoreArts = artScoresUser6
        )
    }

    object User7 {
        private val musicScoreUser7 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 8
        )
        private val theatreScoreUser7 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 0
        )
        private val paintingScoreUser7 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 12
        )
        private val danceScoreUser7 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 17
        )
        private val artScoresUser7 = listOf(musicScoreUser7, theatreScoreUser7, paintingScoreUser7, danceScoreUser7)
        val userEntity7 = UserEntity(
            id = generateId(),
            name = "Вероника Матвеева",
            avatar = "",
            totalScore = artScoresUser7.sumOf { it.score },
            scoreArts = artScoresUser7
        )
    }

    object User8 {
        private val musicScoreUser8 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 20
        )
        private val theatreScoreUser8 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 0
        )
        private val paintingScoreUser8 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 0
        )
        private val danceScoreUser8 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 0
        )
        private val artScoresUser8 = listOf(musicScoreUser8, theatreScoreUser8, paintingScoreUser8, danceScoreUser8)
        val userEntity8 = UserEntity(
            id = generateId(),
            name = "Игорь Бобров",
            avatar = "",
            totalScore = artScoresUser8.sumOf { it.score },
            scoreArts = artScoresUser8
        )
    }

    object User9 {
        private val musicScoreUser9 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 10
        )
        private val theatreScoreUser9 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 5
        )
        private val paintingScoreUser9 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 20
        )
        private val danceScoreUser9 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 7
        )
        private val artScoresUser9 = listOf(musicScoreUser9, theatreScoreUser9, paintingScoreUser9, danceScoreUser9)
        val userEntity9 = UserEntity(
            id = generateId(),
            name = "Екатерина Тетерина",
            avatar = "",
            totalScore = artScoresUser9.sumOf { it.score },
            scoreArts = artScoresUser9
        )
    }

    object User10 {
        private val musicScoreUser10 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 10
        )
        private val theatreScoreUser10 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 5
        )
        private val paintingScoreUser10 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 2
        )
        private val danceScoreUser10 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 7
        )
        private val artScoresUser10 = listOf(musicScoreUser10, theatreScoreUser10, paintingScoreUser10, danceScoreUser10)
        val userEntity10 = UserEntity(
            id = generateId(),
            name = "Андрей Рублев",
            avatar = "",
            totalScore = artScoresUser10.sumOf { it.score },
            scoreArts = artScoresUser10
        )
    }

    object User11 {
        private val musicScoreUser11 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 0
        )
        private val theatreScoreUser11 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 1
        )
        private val paintingScoreUser11 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 7
        )
        private val danceScoreUser11 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 0
        )
        private val artScoresUser11 = listOf(musicScoreUser11, theatreScoreUser11, paintingScoreUser11, danceScoreUser11)
        val userEntity11 = UserEntity(
            id = generateId(),
            name = "Пабло Пикассо",
            avatar = "",
            totalScore = artScoresUser11.sumOf { it.score },
            scoreArts = artScoresUser11
        )
    }

    object User12 {
        private val musicScoreUser12 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 0
        )
        private val theatreScoreUser12 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 5
        )
        private val paintingScoreUser12 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 12
        )
        private val danceScoreUser12 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 8
        )
        private val artScoresUser12 = listOf(musicScoreUser12, theatreScoreUser12, paintingScoreUser12, danceScoreUser12)
        val userEntity12 = UserEntity(
            id = generateId(),
            name = "Леонардо Да Винчи",
            avatar = "",
            totalScore = artScoresUser12.sumOf { it.score },
            scoreArts = artScoresUser12
        )
    }

    object User13 {
        private val musicScoreUser13 = ArtScore(
            artType = TaskArtType.MUSIC,
            score = 1
        )
        private val theatreScoreUser13 = ArtScore(
            artType = TaskArtType.THEATRE,
            score = 11
        )
        private val paintingScoreUser13 = ArtScore(
            artType = TaskArtType.PAINTING,
            score = 15
        )
        private val danceScoreUser13 = ArtScore(
            artType = TaskArtType.DANCE,
            score = 0
        )
        private val artScoresUser13 = listOf(musicScoreUser13, theatreScoreUser13, paintingScoreUser13, danceScoreUser13)
        val userEntity13 = UserEntity(
            id = generateId(),
            name = "Андрей Васнецов",
            avatar = "",
            totalScore = artScoresUser13.sumOf { it.score },
            scoreArts = artScoresUser13
        )
    }

    val users = listOf(User1.userEntity1, User2.userEntity2, User3.userEntity3, User4.userEntity4, User5.userEntity5, User6.userEntity6, User7.userEntity7, User8.userEntity8, User9.userEntity9, User10.userEntity10, User11.userEntity11, User12.userEntity12, User13.userEntity13)

}