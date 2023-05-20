package com.culture.estet.ui.presentation.elements

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.culture.estet.R
import com.culture.estet.ui.presentation.states.AppTopBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    isShowNavigateBack: Boolean,
) {
    var isExpanded: Boolean by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(),
        actions = {

            IconButton(
                onClick = {
                    isExpanded = !isExpanded
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_menu),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }

        }
    )
}