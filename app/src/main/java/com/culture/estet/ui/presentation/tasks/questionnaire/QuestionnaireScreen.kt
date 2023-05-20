package com.culture.estet.ui.presentation.tasks.questionnaire

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState

@Composable
fun QuestionnaireScreen(
    userId: String
) {
    val appState = LocalAppScreenState.current

    LaunchedEffect(Unit) {
        appState.shouldShowBottomBar.value = false
    }
    Text(text = "QuestionnaireScreen: $userId")
    
}