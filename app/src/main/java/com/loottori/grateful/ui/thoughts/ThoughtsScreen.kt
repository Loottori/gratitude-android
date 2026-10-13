package com.loottori.grateful.ui.thoughts

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
import com.loottori.grateful.data.model.Thought
import com.loottori.grateful.ui.UiState
import com.loottori.grateful.ui.components.DailyCard
import com.loottori.grateful.ui.components.ErrorView
import com.loottori.grateful.ui.components.ListItemCard
import com.loottori.grateful.ui.components.LoadingView
import com.loottori.grateful.ui.components.itemOfTheDay

@Composable
fun ThoughtsScreen(
    modifier: Modifier = Modifier,
    viewModel: ThoughtsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> LoadingView(modifier)
        is UiState.Error -> ErrorView(state.message, onRetry = viewModel::loadThoughts, modifier)
        is UiState.Success -> ThoughtList(state.items, modifier)
    }
}

@Composable
private fun ThoughtList(thoughts: List<Thought>, modifier: Modifier = Modifier) {
    val today = thoughts.itemOfTheDay()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (today != null) {
            item {
                DailyCard(
                    label = "Thought of the day",
                    text = today.text,
                    source = today.author
                )
            }
        }
        item {
            Text(
                text = "All thoughts",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        items(thoughts, key = { it.id }) { thought ->
            ListItemCard(text = thought.text, source = thought.author)
        }
    }
}