package com.vicky7230.newsapp.data.network

import com.google.gson.JsonElement
import retrofit2.Response


interface ApiHelper {

    suspend fun getSources(
        apiKey: String = "dffb063c4fb04b9492497adce8b7aea6"
    ): Response<JsonElement>

    suspend fun getNewsFromSource(
        apiKey: String = "dffb063c4fb04b9492497adce8b7aea6",
        sources: String
    ): Response<JsonElement>
}