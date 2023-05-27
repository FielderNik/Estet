package com.culture.estet.ui.presentation.courses

import com.culture.estet.ui.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(

) : BaseViewModel<CoursesScreenState, CoursesEffect, CoursesAction>(CoursesScreenState.Loading) {

    override fun sendAction(action: CoursesAction) {
        launchOnMain {

        }
    }
}