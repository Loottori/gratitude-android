package com.loottori.grateful.ui.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.loottori.grateful.data.model.JournalEntry
import com.loottori.grateful.data.repository.JournalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val journal = JournalRepository.getInstance(application)

    /** Merkinnät uusimmasta vanhimpaan. Päivittyy automaattisesti, kun jotain tallennetaan. */
    val entries: StateFlow<List<JournalEntry>> = journal.entries
        .map { toSortedEntries(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, toSortedEntries(journal.entries.value))

    private fun toSortedEntries(map: Map<String, List<String>>): List<JournalEntry> =
        map.filterValues { it.isNotEmpty() }
            .map { (date, items) -> JournalEntry(date, items) }
            .sortedByDescending { it.date }
}