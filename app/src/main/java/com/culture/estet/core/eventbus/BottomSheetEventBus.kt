package com.culture.estet.core.eventbus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BottomSheetEventBus(private val coroutineScope: CoroutineScope) {
    private val _bottomSheetState = MutableStateFlow<Boolean>(false)
    val bottomSheetState = _bottomSheetState.asStateFlow()

    var bottomSheetContent: MutableState<(@Composable () -> Unit)?> = mutableStateOf(null)

    fun show(
        content: @Composable () -> Unit
    ) {
        coroutineScope.launch {
            bottomSheetContent.value = content
            _bottomSheetState.emit(true)
        }

    }

    fun hide() {
        coroutineScope.launch {
            bottomSheetContent.value = null
            _bottomSheetState.emit(false)
        }
    }


}

@Composable
fun rememberBottomSheetEventBus(coroutineScope: CoroutineScope) : BottomSheetEventBus {
    return remember {
        BottomSheetEventBus(coroutineScope)
    }
}