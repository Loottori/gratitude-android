package com.loottori.grateful.ui.thoughts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loottori.grateful.data.model.Thought
import com.loottori.grateful.data.repository.ContentRepository
import com.loottori.grateful.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThoughtsViewModel : ViewModel() {

    private val repository = ContentRepository()

    private val _uiState = MutableStateFlow<UiState<Thought>>(UiState.Loading)
    val uiState: StateFlow<UiState<Thought>> = _uiState.asStateFlow()

    init {
        loadThoughts()
    }

    fun loadThoughts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                _uiState.value = UiState.Success(repository.getThoughts())
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    "Could not load thoughts. Check your internet connection."
                )
            }
        }
    }
}