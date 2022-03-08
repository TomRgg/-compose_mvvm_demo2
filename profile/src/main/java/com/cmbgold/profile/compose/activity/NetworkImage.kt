package com.cmbgold.profile.compose.activity

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun NetworkProfileImage(
    url: String,
    previewPlaceholderId: Int? = 0,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    Image(
        painter = rememberGlidePainter(
            url,
            fadeIn = true,
            previewPlaceholder = previewPlaceholderId ?: 0
        ),
        contentDescription = "",
        modifier = modifier,
        contentScale = contentScale
    )
}