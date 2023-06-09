package com.culture.estet.ui.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.culture.estet.ui.presentation.navigation.TopLevelDestination
import com.culture.estet.ui.theme.DarkPastelPurple
import com.culture.estet.ui.theme.LightPastelPurple
import com.culture.estet.ui.theme.Purple500

@Composable
fun AppBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(windowInsets)
            .height(56.dp)
            .background(color = if (isSystemInDarkTheme()) DarkPastelPurple else LightPastelPurple),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            AppNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                    )
                },

            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false


@Composable
private fun RowScope.AppNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = false,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = if (isSystemInDarkTheme()) LightPastelPurple else Purple500,
            unselectedIconColor = Color.White,
            selectedTextColor = Purple500,
            unselectedTextColor = Color.White,
            indicatorColor = if (isSystemInDarkTheme()) DarkPastelPurple else LightPastelPurple,
        ),
    )
}