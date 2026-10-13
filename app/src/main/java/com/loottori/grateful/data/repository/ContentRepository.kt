package com.loottori.grateful.data.repository

import com.loottori.grateful.data.model.Thought
import com.loottori.grateful.data.model.Verse
import com.loottori.grateful.data.remote.ApiService
import com.loottori.grateful.data.remote.RetrofitClient

class ContentRepository(
    private val api: ApiService = RetrofitClient.api
) {
    suspend fun getVerses(): List<Verse> = api.getVerses()

    suspend fun getThoughts(): List<Thought> = api.getThoughts()
}