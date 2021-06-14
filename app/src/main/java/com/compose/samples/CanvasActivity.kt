package com.compose.samples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * CanvasActivity
 * Created by wangzhen on 2021/6/14.
 */
class CanvasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateCanvas()
        }
    }

    @Composable
    private fun CreateCanvas() {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            drawRect(
                color = Color(0xff3965b0),
                size = size / 2f
            )
            drawRect(
                color = Color(0xff3965b0),
                size = size / 2f,
                topLeft = Offset(width / 2, height / 2)
            )

            drawCircle(
                color = Color(0xff707070),
                center = Offset(width / 2, height / 2),
                radius = width / 4,
                style = Stroke(width = 10f)
            )
        }
    }
}