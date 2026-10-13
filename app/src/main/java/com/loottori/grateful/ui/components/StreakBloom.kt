package com.loottori.grateful.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.loottori.grateful.ui.theme.Border
import com.loottori.grateful.ui.theme.Peach
import com.loottori.grateful.ui.theme.RoseDeep
import com.loottori.grateful.ui.theme.Sage
import com.loottori.grateful.ui.theme.SageInk
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Kukka, jonka terälehdet aukeavat putken kasvaessa (enintään 6).
 * Piirretään samoilla mitoilla kuin alkuperäisen sovelluksen SVG-kukka.
 */
@Composable
fun StreakBloom(openPetals: Int, modifier: Modifier = Modifier, diameter: Dp = 22.dp) {
    Canvas(modifier = modifier.size(diameter)) {
        val unit = size.minDimension / 24f
        val middle = Offset(size.width / 2, size.height / 2)

        for (i in 0 until 6) {
            val angle = 2 * PI * i / 6 - PI / 2
            val petalCenter = Offset(
                middle.x + cos(angle).toFloat() * 6.2f * unit,
                middle.y + sin(angle).toFloat() * 6.2f * unit
            )
            val isOpen = i < openPetals
            drawCircle(
                color = if (isOpen) RoseDeep else Border,
                radius = 5f * unit,
                center = petalCenter,
                alpha = if (isOpen) 0.9f else 0.6f
            )
        }
        drawCircle(color = Peach, radius = 4.6f * unit, center = middle)
    }
}

/** Vihreä pilleri, jossa kukka ja teksti "3-day streak". */
@Composable
fun StreakBadge(streak: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(Sage, RoundedCornerShape(50))
            .padding(start = 8.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StreakBloom(openPetals = streak.coerceAtMost(6))
        Text(
            text = "$streak-day streak",
            style = MaterialTheme.typography.labelLarge,
            color = SageInk
        )
    }
}

