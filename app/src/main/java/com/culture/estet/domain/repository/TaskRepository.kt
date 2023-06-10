package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.core.funcional.flatMap
import com.culture.estet.core.funcional.toRight
import com.culture.estet.core.generateId
import com.culture.estet.data.mock.Tasks
import com.culture.estet.data.tasks.questions.local.QuestionEntity
import com.culture.estet.data.tasks.statistics.local.StatisticsEntity
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tasks.TaskCategory
import com.culture.estet.domain.models.tasks.TaskLevel
import javax.inject.Inject

interface TaskRepository {
    suspend fun getTaskCategoriesByUserId(userId: String): Either<Failure, List<TaskCategory>>
    suspend fun getLevelData(userId: String, artType: ArtType): Either<Failure, List<TaskLevel>>
}


class TaskRepositoryImpl @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val statisticsRepository: StatisticsRepository,
) : TaskRepository, BaseRepository() {

    override suspend fun getTaskCategoriesByUserId(userId: String): Either<Failure, List<TaskCategory>> {
        return questionRepository.getAllQuestions().flatMap { questions ->
            statisticsRepository.getAllStatistics(userId).flatMap { statistics ->
                QuestionsStatisticsToTaskCategoriesMapper().map(questions, statistics).toRight()
            }
        }
    }

    override suspend fun getLevelData(userId: String, artType: ArtType): Either<Failure, List<TaskLevel>> {
        return handleRequest {
            Tasks.Level.allLevels.filter { it.artType == artType }
        }
    }

}

class QuestionsStatisticsToTaskCategoriesMapper {
    fun map(questions: List<QuestionEntity>, statistics: List<StatisticsEntity>): List<TaskCategory> {
        return ArtType.values().map {

            TaskCategory(
                id = generateId(),
                type = it,
                amountLevels = questions.size,
                completedLevels = getCompletedQuestions(questions, statistics).size,
                ordinal = it.ordinal,
                amountArtScore = 0
            )
        }

    }

    private fun getCompletedQuestions(questions: List<QuestionEntity>, statistics: List<StatisticsEntity>): List<QuestionEntity> {
        return questions.filter { question ->
            statistics.any { it.questionId ==  question.id}
        }

    }
}