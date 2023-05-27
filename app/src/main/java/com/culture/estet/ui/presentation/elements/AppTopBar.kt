package com.culture.estet.ui.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.culture.estet.R
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.theme.LightPastelPurple

@Composable
fun AppTopBar(
    title: String,
    isShowNavigateBack: Boolean,
) {
    val appState = LocalAppScreenState.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = LightPastelPurple)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(modifier = Modifier
            .fillMaxHeight()
            .widthIn(36.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isShowNavigateBack) {
                IconButton(
                    onClick = {
                        appState.onBackClick()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_back),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Box(modifier = Modifier
            .fillMaxHeight()
            .widthIn(36.dp), contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    appState.onBackClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_profile),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

    }
}