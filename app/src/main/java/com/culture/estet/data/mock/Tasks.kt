package com.culture.estet.data.mock

import com.culture.estet.domain.models.tasks.TaskCategory
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tasks.TaskLevel
import com.culture.estet.domain.models.tasks.TaskLevelType

object Tasks {

    object TasksCategory {
        private val music = TaskCategory(
            id = "music_id",
            type = ArtType.MUSIC,
            amountLevels = 5,
            completedLevels = 3,
            amountArtScore = 10,
            ordinal = 1
        )

        private val theatre = TaskCategory(
            id = "theatre_id",
            type = ArtType.THEATRE,
            amountLevels = 5,
            completedLevels = 2,
            amountArtScore = 5,
            ordinal = 2
        )

        private val dance = TaskCategory(
            id = "dance_id",
            type = ArtType.DANCE,
            amountLevels = 5,
            completedLevels = 0,
            amountArtScore = 0,
            ordinal = 3
        )

        private val painting = TaskCategory(
            id = "painting_id",
            type = ArtType.PAINTING,
            amountLevels = 5,
            completedLevels = 0,
            amountArtScore = 0,
            ordinal = 4
        )


        val taskCategories = listOf<TaskCategory>(
            music, dance, painting, theatre
        )
    }

    object Level {
        private val easyTheatre = TaskLevel(
            artType = ArtType.THEATRE,
            taskLevelType = TaskLevelType.BEGINNER,
            amountQuestions = 3,
            completedQuestions = 1,
        )

        private val advancedTheatre = TaskLevel(
            artType = ArtType.THEATRE,
            taskLevelType = TaskLevelType.ADVANCED,
            amountQuestions = 3,
            completedQuestions = 0,
        )

        private val expertTheatre = TaskLevel(
            artType = ArtType.THEATRE,
            taskLevelType = TaskLevelType.EXPERT,
            amountQuestions = 3,
            completedQuestions = 3,
        )

        private val easyDance = TaskLevel(
            artType = ArtType.DANCE,
            taskLevelType = TaskLevelType.BEGINNER,
            amountQuestions = 3,
            completedQuestions = 1,
        )

        private val advancedDance = TaskLevel(
            artType = ArtType.DANCE,
            taskLevelType = TaskLevelType.ADVANCED,
            amountQuestions = 3,
            completedQuestions = 0,
        )

        private val expertDance = TaskLevel(
            artType = ArtType.DANCE,
            taskLevelType = TaskLevelType.EXPERT,
            amountQuestions = 3,
            completedQuestions = 3,
        )

        private val easyPainting = TaskLevel(
            artType = ArtType.PAINTING,
            taskLevelType = TaskLevelType.BEGINNER,
            amountQuestions = 3,
            completedQuestions = 1,
        )

        private val advancedPainting = TaskLevel(
            artType = ArtType.PAINTING,
            taskLevelType = TaskLevelType.ADVANCED,
            amountQuestions = 3,
            completedQuestions = 0,
        )

        private val expertPainting = TaskLevel(
            artType = ArtType.PAINTING,
            taskLevelType = TaskLevelType.EXPERT,
            amountQuestions = 3,
            completedQuestions = 3,
        )

        private val easyMusic = TaskLevel(
            artType = ArtType.MUSIC,
            taskLevelType = TaskLevelType.BEGINNER,
            amountQuestions = 3,
            completedQuestions = 1,
        )

        private val advancedMusic = TaskLevel(
            artType = ArtType.MUSIC,
            taskLevelType = TaskLevelType.ADVANCED,
            amountQuestions = 3,
            completedQuestions = 0,
        )

        private val expertMusic = TaskLevel(
            artType = ArtType.MUSIC,
            taskLevelType = TaskLevelType.EXPERT,
            amountQuestions = 3,
            completedQuestions = 3,
        )

        val allLevels = listOf(easyDance, easyMusic, easyPainting, easyTheatre, advancedDance, advancedTheatre, advancedPainting, advancedMusic, expertDance, expertMusic, expertPainting, expertTheatre)
    }
}