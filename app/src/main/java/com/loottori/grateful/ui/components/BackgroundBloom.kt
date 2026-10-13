package com.loottori.grateful.ui.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.loottori.grateful.ui.theme.Lavender
import com.loottori.grateful.ui.theme.Peach

/**
 * Pehmeät pastellihehkut taustalla, kuten alkuperäisen verkkosovelluksen "bg-bloom":
 * laventeli vasemmassa yläkulmassa ja persikka oikeassa alakulmassa.
 */
fun Modifier.backgroundBloom(): Modifier = drawBehind {
    val topCenter = Offset(0f, 0f)
    val topRadius = size.width * 0.6f
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Lavender.copy(alpha = 0.55f), Color.Transparent),
            center = topCenter,
            radius = topRadius
        ),
        radius = topRadius,
        center = topCenter
    )

    val bottomCenter = Offset(size.width, size.height)
    val bottomRadius = size.width * 0.55f
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Peach.copy(alpha = 0.7f), Color.Transparent),
            center = bottomCenter,
            radius = bottomRadius
        ),
        radius = bottomRadius,
        center = bottomCenter
    )
}

