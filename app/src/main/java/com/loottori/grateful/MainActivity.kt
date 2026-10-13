package com.loottori.grateful

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.loottori.grateful.ui.theme.GratefulTheme
import com.loottori.grateful.ui.thoughts.ThoughtsScreen
import com.loottori.grateful.ui.verses.VersesScreen
import androidx.compose.material.icons.filled.Home
import com.loottori.grateful.ui.today.TodayScreen
import androidx.compose.material.icons.filled.DateRange
import com.loottori.grateful.ui.history.HistoryScreen
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import com.loottori.grateful.ui.components.backgroundBloom

enum class AppDestination(val label: String, val icon: ImageVector) {
    TODAY("Today", Icons.Filled.Home),
    HISTORY("History", Icons.Filled.DateRange),
    VERSES("Verses", Icons.Filled.Star),
    THOUGHTS("Thoughts", Icons.Filled.Favorite)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            GratefulTheme {
                GratefulApp()
            }
        }
    }
}

@Composable
fun GratefulApp() {
    var current by rememberSaveable { mutableStateOf(AppDestination.TODAY) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestination.entries.forEach { destination ->
                item(
                    selected = destination == current,
                    onClick = { current = destination },
                    icon = { Icon(destination.icon, contentDescription = null) },
                    label = { Text(destination.label) }
                )
            }
        }
    ) {
        // Pastellihehku koko sovelluksen taustalla
        Box(
            modifier = Modifier
                .fillMaxSize()
                .backgroundBloom()
        ) {
            val contentModifier = Modifier.statusBarsPadding()
            when (current) {
                AppDestination.TODAY -> TodayScreen(contentModifier)
                AppDestination.HISTORY -> HistoryScreen(contentModifier)
                AppDestination.VERSES -> VersesScreen(contentModifier)
                AppDestination.THOUGHTS -> ThoughtsScreen(contentModifier)
            }
        }
    }
}