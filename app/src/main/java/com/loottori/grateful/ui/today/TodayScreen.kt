package com.loottori.grateful.ui.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loottori.grateful.ui.components.DailyCard
import com.loottori.grateful.ui.components.StreakBadge
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import com.loottori.grateful.ui.theme.Lavender
import com.loottori.grateful.ui.theme.Peach
import com.loottori.grateful.ui.theme.RoseDeep
import com.loottori.grateful.ui.theme.SageDeep
import com.loottori.grateful.ui.components.GratefulHeader

/** Pallojen värit vuorotellen, samat kuin History-korttien reunoissa */
private val DotColors = listOf(Lavender, RoseDeep, Peach, SageDeep)
@Composable
fun TodayScreen(
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel = viewModel()
) {
    val verse by viewModel.dailyVerse.collectAsState()
    val streak by viewModel.streak.collectAsState()
    val today = remember {
        LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        GratefulHeader()

        // Netistä haettu päivän jae, tai oletuslause jos haku ei onnistu
        DailyCard(
            label = if (verse != null) "Verse of the day" else "A thought for today",
            text = verse?.text ?: "Small moments of gratitude make big differences.",
            source = verse?.reference ?: "Grateful"
        )

        // Kirjauslomake valkoisessa kortissa
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = today,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f)
                    )
                    if (streak > 0) {
                        StreakBadge(streak = streak)
                    }
                }

                Text(
                    text = "What are you grateful for today?",
                    style = MaterialTheme.typography.titleLarge
                )

                viewModel.items.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value = value,
                        onValueChange = { viewModel.updateItem(index, it) },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Something you're grateful for...") },
                        singleLine = true,
                        leadingIcon = {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(DotColors[index % DotColors.size], CircleShape)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { viewModel.removeItem(index) }) {
                                Icon(Icons.Filled.Close, contentDescription = "Remove this item")
                            }
                        }
                    )
                }

                TextButton(onClick = viewModel::addItem) {
                    Text("+ Add another thing")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(onClick = viewModel::save) {
                        Text("Save today's entry")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = viewModel.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}