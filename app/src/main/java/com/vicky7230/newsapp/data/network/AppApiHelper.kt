package com.vicky7230.newsapp.data.network


import com.google.gson.JsonElement
import retrofit2.Response
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getSources(apiKey: String): Response<JsonElement> {
        return apiService.getSources()
    }

    override suspend fun getNewsFromSource(apiKey: String, sources: String): Response<JsonElement> {
        return apiService.getNewsFromSource(sources = sources)
    }

}