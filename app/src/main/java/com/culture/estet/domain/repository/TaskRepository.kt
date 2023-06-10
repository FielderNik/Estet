package com.culture.estet.domain.repository

import com.culture.estet.core.funcional.Either
import com.culture.estet.core.funcional.Failure
import com.culture.estet.data.mock.Tasks
import com.culture.estet.domain.models.ArtType
import com.culture.estet.domain.models.tasks.TaskCategory
import com.culture.estet.domain.models.tasks.TaskLevel
import javax.inject.Inject

interface TaskRepository {
    suspend fun getTaskCategoriesByUserId(userId: String): Either<Failure, List<TaskCategory>>
    suspend fun getLevelData(userId: String, artType: ArtType): Either<Failure, List<TaskLevel>>
}


class TaskRepositoryImpl @Inject constructor() : TaskRepository, BaseRepository() {

    override suspend fun getTaskCategoriesByUserId(userId: String): Either<Failure, List<TaskCategory>> {
        return handleRequest {
            Tasks.TasksCategory.taskCategories
        }
    }

    override suspend fun getLevelData(userId: String, artType: ArtType): Either<Failure, List<TaskLevel>> {
        return handleRequest {
            Tasks.Level.allLevels.filter { it.artType == artType }
        }
    }

}