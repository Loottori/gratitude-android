package com.loottori.grateful.ui.verses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loottori.grateful.data.model.Verse
import com.loottori.grateful.data.repository.ContentRepository
import com.loottori.grateful.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VersesViewModel : ViewModel() {

    private val repository = ContentRepository()

    private val _uiState = MutableStateFlow<UiState<Verse>>(UiState.Loading)
    val uiState: StateFlow<UiState<Verse>> = _uiState.asStateFlow()

    init {
        loadVerses()
    }

    fun loadVerses() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                _uiState.value = UiState.Success(repository.getVerses())
            } catch (e: Exception) {
                _uiState.value = UiState.Error(
                    "Could not load verses. Check your internet connection."
                )
            }
        }
    }
}