package com.vicky7230.newsapp.data.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String = "dffb063c4fb04b9492497adce8b7aea6"
    ): Response<JsonElement>

    @GET("top-headlines")
    suspend fun getNewsFromSource(
        @Query("apiKey") apiKey: String = "dffb063c4fb04b9492497adce8b7aea6",
        @Query("sources") sources: String
    ): Response<JsonElement>
}