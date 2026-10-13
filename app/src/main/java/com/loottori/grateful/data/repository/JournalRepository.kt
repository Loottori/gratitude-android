package com.loottori.grateful.data.repository

import android.content.Context
import com.loottori.grateful.data.local.EntryStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

/** Tämän päivän avain muodossa 2026-10-10. */
fun todayKey(): String = LocalDate.now().toString()

/** Montako päivää peräkkäin on kirjattu. Jos tänään ei ole vielä kirjattu, lasketaan eilisestä. */
fun calculateStreak(entries: Map<String, List<String>>): Int {
    var day = LocalDate.now()
    if (entries[day.toString()].isNullOrEmpty()) day = day.minusDays(1)
    var streak = 0
    while (!entries[day.toString()].isNullOrEmpty()) {
        streak++
        day = day.minusDays(1)
    }
    return streak
}

class JournalRepository private constructor(private val storage: EntryStorage) {

    private val _entries = MutableStateFlow(storage.load())
    val entries: StateFlow<Map<String, List<String>>> = _entries.asStateFlow()

    fun saveEntry(date: String, items: List<String>) = update(_entries.value + (date to items))

    fun deleteEntry(date: String) = update(_entries.value - date)

    private fun update(newEntries: Map<String, List<String>>) {
        _entries.value = newEntries
        storage.save(newEntries)
    }

    companion object {
        @Volatile
        private var instance: JournalRepository? = null

        fun getInstance(context: Context): JournalRepository =
            instance ?: synchronized(this) {
                instance ?: JournalRepository(EntryStorage(context.applicationContext))
                    .also { instance = it }
            }
    }
}

