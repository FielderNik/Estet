package com.culture.estet.core.eventbus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DialogEventBus(private val coroutineScope: CoroutineScope) {
    private val _dialogState = MutableStateFlow<Boolean>(false)
    val dialogState = _dialogState.asStateFlow()

    var dialog: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)

    fun showDialog(
        content: @Composable () -> Unit
    ) {
        coroutineScope.launch {
            dialog.value = content
            _dialogState.emit(true)
        }

    }

    fun hideDialog() {
        coroutineScope.launch {
            dialog.value = null
            _dialogState.emit(false)
        }
    }


}

@Composable
fun rememberDialogEventBus(coroutineScope: CoroutineScope) : DialogEventBus {
    return remember {
        DialogEventBus(coroutineScope)
    }
}