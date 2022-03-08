package com.xiangxue.arch_demo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview

class FlashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent {
            showFlashBackground()
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(FlashActivity@ this, MainActivity::class.java))
                finish()
            }, 1000)
        }
    }

    @Preview
    @Composable
    private fun showFlashBackground() {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                ImageBitmap.imageResource(id = R.mipmap.flash_bg_2),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}