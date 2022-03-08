package com.xiangxue.base.compose.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.xiangxue.base.R

@Composable
fun TitleComposeView(title: String, showBack: Boolean, onBack: (() -> Unit)?) {
    if (showBack) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .wrapContentSize(Alignment.Center)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = {
                IconButton(onClick = {
                    onBack?.invoke()
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
            backgroundColor = colorResource(id = R.color.colorPrimary),
            contentColor = Color.White,
        )
    } else {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .wrapContentSize(Alignment.Center)
                )
            },
            backgroundColor = colorResource(id = R.color.colorPrimary),
            contentColor = Color.White,

            )
    }
}