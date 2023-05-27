package com.culture.estet.ui.presentation.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.culture.estet.domain.models.tasks.TaskArtType

@Composable
fun ArtImage(
    modifier: Modifier = Modifier,
    size: Dp,
    artType: TaskArtType,
) {
    Image(
        modifier = modifier.size(size),
        painter = painterResource(id = artType.painterSource()),
        contentDescription = null
    )
}