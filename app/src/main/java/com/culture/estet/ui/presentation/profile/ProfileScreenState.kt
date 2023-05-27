package com.culture.estet.ui.presentation.profile

sealed class ProfileScreenState {
    object Loading : ProfileScreenState()

    data class ProfileState(val userId: String?) : ProfileScreenState()
}

sealed class ProfileEffect {

}

sealed class ProfileAction {

}