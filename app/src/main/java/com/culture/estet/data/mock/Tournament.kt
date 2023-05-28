package com.culture.estet.data.mock

import com.culture.estet.core.generateUserId
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
        private val userId1 = generateUserId(1)
        val userEntity1 = UserEntity(
            id = userId1,
            name = "Иван Петров",
            avatar = "https://i.pravatar.cc/100?u=${userId1}",
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
        private val userId2 = generateUserId(2)
        val userEntity2 = UserEntity(
            id = userId2,
            name = "Петр Иванов",
            avatar = "https://i.pravatar.cc/100?u=${userId2}",
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
        private val userId3 = generateUserId(3)
        val userEntity3 = UserEntity(
            id = userId3,
            name = "Елена Зорина",
            avatar = "https://i.pravatar.cc/100?u=${userId3}",
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
        private val userId4 = generateUserId(4)
        val userEntity4 = UserEntity(
            id = userId4,
            name = "Анна Смирнова",
            avatar = "https://i.pravatar.cc/100?u=${userId4}",
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
        private val userId5 = generateUserId(5)

        val userEntity5 = UserEntity(
            id = userId5,
            name = "Екатерина Волкова",
            avatar = "https://i.pravatar.cc/100?u=${userId5}",
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
        private val userId6 = generateUserId(6)

        val userEntity6 = UserEntity(
            id = userId6,
            name = "Александр Соколов",
            avatar = "https://i.pravatar.cc/100?u=${userId6}",
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
        private val userId7 = generateUserId(7)

        val userEntity7 = UserEntity(
            id = userId7,
            name = "Вероника Матвеева",
            avatar = "https://i.pravatar.cc/100?u=${userId7}",
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
        private val userId8 = generateUserId(8)

        val userEntity8 = UserEntity(
            id = userId8,
            name = "Игорь Бобров",
            avatar = "https://i.pravatar.cc/100?u=${userId8}",
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
        private val userId9 = generateUserId(9)

        val userEntity9 = UserEntity(
            id = userId9,
            name = "Екатерина Тетерина",
            avatar = "https://i.pravatar.cc/100?u=${userId9}",
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
        private val userId10 = generateUserId(10)

        val userEntity10 = UserEntity(
            id = userId10,
            name = "Андрей Рублев",
            avatar = "https://i.pravatar.cc/100?u=${userId10}",
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
        private val userId11 = generateUserId(11)

        val userEntity11 = UserEntity(
            id = userId11,
            name = "Пабло Пикассо",
            avatar = "https://i.pravatar.cc/100?u=${userId11}",
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
        private val userId12 = generateUserId(12)

        val userEntity12 = UserEntity(
            id = userId12,
            name = "Леонардо Да Винчи",
            avatar = "https://i.pravatar.cc/100?u=${userId12}",
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
        private val userId13 = generateUserId(13)

        val userEntity13 = UserEntity(
            id = userId13,
            name = "Андрей Васнецов",
            avatar = "https://i.pravatar.cc/100?u=${userId13}",
            totalScore = artScoresUser13.sumOf { it.score },
            scoreArts = artScoresUser13
        )
    }

    val users = listOf(User1.userEntity1, User2.userEntity2, User3.userEntity3, User4.userEntity4, User5.userEntity5, User6.userEntity6, User7.userEntity7, User8.userEntity8, User9.userEntity9, User10.userEntity10, User11.userEntity11, User12.userEntity12, User13.userEntity13)

}