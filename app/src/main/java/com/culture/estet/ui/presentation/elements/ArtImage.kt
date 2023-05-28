package com.culture.estet.ui.presentation.elements

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.culture.estet.domain.models.tasks.TaskArtType

@Composable
fun ArtImage(
    modifier: Modifier = Modifier,
    artType: TaskArtType,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = artType.painterSource()),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}