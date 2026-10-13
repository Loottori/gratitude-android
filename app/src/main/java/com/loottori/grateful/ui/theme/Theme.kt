package com.loottori.grateful.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val GratefulColors = lightColorScheme(
    primary = LavenderButton,
    onPrimary = White,
    primaryContainer = LavenderSoft,
    onPrimaryContainer = Ink,
    secondary = RoseDeep,
    onSecondary = White,
    secondaryContainer = PinkSoft,
    onSecondaryContainer = Ink,
    tertiary = SageDeep,
    onTertiary = White,
    tertiaryContainer = Sage,
    onTertiaryContainer = SageInk,
    background = Paper,
    onBackground = Ink,
    surface = White,
    onSurface = Ink,
    surfaceVariant = LavenderSoft,
    onSurfaceVariant = InkMuted,
    outline = Lavender,
    outlineVariant = Border,
    surfaceContainerLowest = White,
    surfaceContainerLow = Color(0xFFFBF8FD),
    surfaceContainer = Color(0xFFF7F2FB),
    surfaceContainerHigh = Color(0xFFF2ECF8),
    surfaceContainerHighest = LavenderSoft
)

// Pyöreät kulmat kuten alkuperäisessä (14, 22 ja 32 px)
private val GratefulShapes = Shapes(
    extraSmall = RoundedCornerShape(14.dp),
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(22.dp),
    large = RoundedCornerShape(32.dp)
)

@Composable
fun GratefulTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GratefulColors,
        typography = Typography,
        shapes = GratefulShapes,
        content = content
    )
}