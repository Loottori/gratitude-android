package com.loottori.grateful.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loottori.grateful.ui.theme.Fraunces
import com.loottori.grateful.ui.theme.SageDeep
import com.loottori.grateful.ui.theme.White

/**
 * Gratefulin lehtilogo. Piirretty samoilla mitoilla kuin alkuperäisen
 * verkkosovelluksen SVG-logo (24 x 24 yksikön ruudukko).
 */
@Composable
fun BrandLeaf(modifier: Modifier = Modifier, diameter: Dp = 24.dp) {
    Canvas(modifier = modifier.size(diameter)) {
        val u = size.minDimension / 24f

        val leaf = Path().apply {
            moveTo(12f * u, 4f * u)
            cubicTo(16f * u, 6.5f * u, 19f * u, 10.5f * u, 19f * u, 14.5f * u)
            arcTo(Rect(5f * u, 7.5f * u, 19f * u, 21.5f * u), 0f, 180f, false)
            cubicTo(5f * u, 10.5f * u, 8f * u, 6.5f * u, 12f * u, 4f * u)
            close()
        }
        drawPath(path = leaf, color = SageDeep)

        // Lehden keskisuoni
        drawLine(
            color = White,
            start = Offset(12f * u, 4f * u),
            end = Offset(12f * u, 20f * u),
            strokeWidth = 1.2f * u
        )
    }
}

/** Otsikkorivi: lehtilogo ja nimi "Grateful". */
@Composable
fun GratefulHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BrandLeaf()
        Text(
            text = "Grateful",
            style = TextStyle(
                fontFamily = Fraunces,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Normal,
                fontSize = 23.sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

