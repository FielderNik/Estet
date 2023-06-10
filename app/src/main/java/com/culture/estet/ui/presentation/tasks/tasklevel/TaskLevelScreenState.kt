package com.culture.estet.ui.presentation.tasks.tasklevel

import com.culture.estet.domain.models.tasks.TaskLevel
import com.culture.estet.domain.models.ArtType

sealed class TaskLevelScreenState {
    object Loading : TaskLevelScreenState()

    data class Levels(
        val levels: List<TaskLevel>
    ) : TaskLevelScreenState()
}

sealed class TaskLevelEffect {

}

sealed class TaskLevelAction {
    data class Initialize(val userId: String, val artType: ArtType) : TaskLevelAction()
}