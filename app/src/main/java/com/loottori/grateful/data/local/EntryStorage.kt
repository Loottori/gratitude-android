package com.loottori.grateful.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

/** Tallentaa merkinnät laitteelle JSON-tiedostoon: päivämäärä -> lista kiitollisuuden aiheita. */
class EntryStorage(context: Context) {

    private val file = File(context.filesDir, "entries.json")
    private val gson = Gson()
    private val type = object : TypeToken<Map<String, List<String>>>() {}.type

    fun load(): Map<String, List<String>> =
        try {
            if (file.exists()) {
                gson.fromJson<Map<String, List<String>>>(file.readText(), type) ?: emptyMap()
            } else {
                emptyMap()
            }
        } catch (e: Exception) {
            emptyMap()
        }

    fun save(entries: Map<String, List<String>>) {
        file.writeText(gson.toJson(entries))
    }
}

