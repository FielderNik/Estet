package com.culture.estet.ui.presentation.profile

import com.culture.estet.data.mock.Profile
import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : BaseViewModel<ProfileScreenState, ProfileEffect, ProfileAction>(ProfileScreenState.ProfileState(Profile.User.userProfile)){

    override fun sendAction(action: ProfileAction) {
        launchOnMain {

        }
    }
}