package com.culture.estet.ui.presentation.profile

import com.culture.estet.domain.models.profile.UserProfile

sealed class ProfileScreenState {
    object Loading : ProfileScreenState()

    data class ProfileState(val user: UserProfile) : ProfileScreenState()
}

sealed class ProfileEffect {

}

sealed class ProfileAction {

}