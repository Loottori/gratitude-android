package com.loottori.grateful.ui.today

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.loottori.grateful.data.model.Verse
import com.loottori.grateful.data.repository.ContentRepository
import com.loottori.grateful.data.repository.JournalRepository
import com.loottori.grateful.data.repository.calculateStreak
import com.loottori.grateful.data.repository.todayKey
import com.loottori.grateful.ui.components.itemOfTheDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodayViewModel(application: Application) : AndroidViewModel(application) {

    private val journal = JournalRepository.getInstance(application)
    private val content = ContentRepository()

    /** Lomakkeen rivit. */
    val items = mutableStateListOf<String>()

    var message by mutableStateOf("")
        private set

    private val _dailyVerse = MutableStateFlow<Verse?>(null)
    val dailyVerse: StateFlow<Verse?> = _dailyVerse.asStateFlow()

    val streak: StateFlow<Int> = journal.entries
        .map { calculateStreak(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, calculateStreak(journal.entries.value))

    init {
        // Täytetään lomake tämän päivän tallennetuilla riveillä, vähintään kolme kenttää.
        items.addAll(journal.entries.value[todayKey()].orEmpty())
        while (items.size < 3) items.add("")

        // Haetaan päivän jae netistä. Jos haku epäonnistuu, näytetään oletusteksti.
        viewModelScope.launch {
            try {
                _dailyVerse.value = content.getVerses().itemOfTheDay()
            } catch (e: Exception) {
                _dailyVerse.value = null
            }
        }
    }

    fun updateItem(index: Int, value: String) {
        items[index] = value.take(140)
        message = ""
    }

    fun addItem() {
        items.add("")
    }

    fun removeItem(index: Int) {
        if (items.size > 1) items.removeAt(index) else items[0] = ""
    }

    fun save() {
        val values = items.map { it.trim() }.filter { it.isNotEmpty() }
        if (values.isEmpty()) {
            message = "Write at least one thing first"
            return
        }
        journal.saveEntry(todayKey(), values)
        message = "Saved"
    }
}
