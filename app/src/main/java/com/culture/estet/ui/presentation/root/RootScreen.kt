package com.culture.estet.ui.presentation.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.culture.estet.ui.presentation.navigation.AppNavHost
import com.culture.estet.ui.presentation.navigation.AppNavigator
import com.culture.estet.ui.presentation.navigation.rememberAppNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    appNavigator: AppNavigator = rememberAppNavigator(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {


    CompositionLocalProvider() {

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .background(Color.Green)
                ) {
                    Text(text = "one")
                    Text(text = "two")
                    Text(text = "three")
                }
            }
        ) {


            AppNavHost(
                navHostController = appNavigator.navController,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

    }
}
