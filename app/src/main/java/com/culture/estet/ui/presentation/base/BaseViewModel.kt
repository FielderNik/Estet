package com.culture.estet.ui.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow


abstract class BaseViewModel<State, Effect, Action>(initialState: State) : ViewModel() {

    private val _state = MutableStateFlow<State>(initialState)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    abstract fun sendAction(action: Action)

    protected suspend fun setState(newState: State) {
        _state.emit(newState)
    }

    protected suspend fun sendEffect(newEffect: Effect) {
        _effect.emit(newEffect)
    }

    protected fun launchOnMain(block: suspend () -> Unit) : Job {
        return viewModelScope.launch(Dispatchers.Main) {
            block()
        }
    }

    protected suspend fun <T> withIo(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.IO){
            block()
        }
    }

}