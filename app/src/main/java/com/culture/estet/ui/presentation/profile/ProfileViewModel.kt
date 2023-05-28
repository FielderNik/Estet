package com.culture.estet.ui.presentation.profile

import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : BaseViewModel<ProfileScreenState, ProfileEffect, ProfileAction>(ProfileScreenState.ProfileState("")){

    override fun sendAction(action: ProfileAction) {
        launchOnMain {

        }
    }
}