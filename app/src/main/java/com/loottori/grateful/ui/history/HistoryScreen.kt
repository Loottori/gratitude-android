package com.loottori.grateful.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loottori.grateful.data.model.JournalEntry
import com.loottori.grateful.ui.components.StreakBloom
import com.loottori.grateful.ui.theme.Lavender
import com.loottori.grateful.ui.theme.Peach
import com.loottori.grateful.ui.theme.RoseDeep
import com.loottori.grateful.ui.theme.SageDeep
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/** Korttien reunavärit vuorotellen, kuten alkuperäisessä */
private val CardAccents = listOf(Lavender, RoseDeep, Peach, SageDeep)

private fun formatLongDate(key: String): String =
    LocalDate.parse(key).format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", Locale.ENGLISH))

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel()
) {
    val entries by viewModel.entries.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(bottom = 4.dp)) {
                Text(
                    text = "Your gratitude, over time",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (entries.isEmpty()) {
                        "Every entry you've written lives here."
                    } else {
                        "${entries.size} ${if (entries.size == 1) "entry" else "entries"} written so far."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (entries.isEmpty()) {
            item { EmptyState() }
        } else {
            itemsIndexed(entries, key = { _, entry -> entry.date }) { index, entry ->
                EntryCard(entry = entry, accent = CardAccents[index % CardAccents.size])
            }
        }
    }
}

@Composable
private fun EntryCard(entry: JournalEntry, accent: Color) {
    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            // Värillinen reuna kortin vasemmalla puolella
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .fillMaxHeight()
                    .background(accent)
            )
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
                Text(
                    text = formatLongDate(entry.date),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                entry.items.forEach { item ->
                    Text(
                        text = "•  $item",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StreakBloom(openPetals = 3, diameter = 56.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Nothing here yet.", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Your first entry will show up as soon as you save one.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}