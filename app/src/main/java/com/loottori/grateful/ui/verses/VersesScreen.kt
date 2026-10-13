package com.loottori.grateful.ui.verses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loottori.grateful.data.model.Verse
import com.loottori.grateful.ui.UiState
import com.loottori.grateful.ui.components.DailyCard
import com.loottori.grateful.ui.components.ErrorView
import com.loottori.grateful.ui.components.ListItemCard
import com.loottori.grateful.ui.components.LoadingView
import com.loottori.grateful.ui.components.itemOfTheDay

@Composable
fun VersesScreen(
    modifier: Modifier = Modifier,
    viewModel: VersesViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> LoadingView(modifier)
        is UiState.Error -> ErrorView(state.message, onRetry = viewModel::loadVerses, modifier)
        is UiState.Success -> VerseList(state.items, modifier)
    }
}

@Composable
private fun VerseList(verses: List<Verse>, modifier: Modifier = Modifier) {
    val today = verses.itemOfTheDay()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (today != null) {
            item {
                DailyCard(
                    label = "Verse of the day",
                    text = today.text,
                    source = today.reference
                )
            }
        }
        item {
            Text(
                text = "All verses",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        items(verses, key = { it.id }) { verse ->
            ListItemCard(text = verse.text, source = verse.reference)
        }
    }
}