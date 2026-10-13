package com.loottori.grateful.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.loottori.grateful.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

/** Otsikkofontti */
val Fraunces = FontFamily(
    Font(R.font.fraunces_semibold, FontWeight.SemiBold),
    Font(R.font.fraunces_italic, FontWeight.Normal, FontStyle.Italic)
)

/** Leipätekstin fontti */
val InstrumentSans = FontFamily(
    Font(R.font.instrument_sans_regular, FontWeight.Normal),
    Font(R.font.instrument_sans_semibold, FontWeight.SemiBold)
)

private val base = Typography()

val Typography = Typography(
    headlineSmall = base.headlineSmall.copy(fontFamily = Fraunces, fontWeight = FontWeight.SemiBold),
    titleLarge = base.titleLarge.copy(fontFamily = Fraunces, fontWeight = FontWeight.SemiBold),
    titleMedium = base.titleMedium.copy(fontFamily = Fraunces, fontWeight = FontWeight.SemiBold),
    bodyLarge = base.bodyLarge.copy(fontFamily = InstrumentSans),
    bodyMedium = base.bodyMedium.copy(fontFamily = InstrumentSans),
    bodySmall = base.bodySmall.copy(fontFamily = InstrumentSans),
    labelLarge = base.labelLarge.copy(fontFamily = InstrumentSans, fontWeight = FontWeight.SemiBold),
    labelMedium = base.labelMedium.copy(fontFamily = InstrumentSans, fontWeight = FontWeight.SemiBold),
    labelSmall = base.labelSmall.copy(fontFamily = InstrumentSans, fontWeight = FontWeight.SemiBold)
)
/** Päivän lauseen tyyli: kursivoitu Fraunces kuten alkuperäisessä */
val QuoteStyle = TextStyle(
    fontFamily = Fraunces,
    fontStyle = FontStyle.Italic,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp,
    lineHeight = 32.sp
)