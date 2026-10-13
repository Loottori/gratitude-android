package com.loottori.grateful.data.remote

import com.loottori.grateful.data.model.Thought
import com.loottori.grateful.data.model.Verse
import retrofit2.http.GET

interface ApiService {

    @GET("verses.json")
    suspend fun getVerses(): List<Verse>

    @GET("thoughts.json")
    suspend fun getThoughts(): List<Thought>
}