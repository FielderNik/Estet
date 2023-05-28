package com.culture.estet.ui.presentation.elements.dialogs

import androidx.annotation.StringRes

data class DialogButton(
    @StringRes
    val labelRes: Int,
    val onClick: () -> Unit,
)
