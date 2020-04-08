package com.vicky7230.newsapp.data

import com.google.gson.JsonElement
import com.vicky7230.newsapp.data.network.AppApiHelper
import retrofit2.Response
import javax.inject.Inject

class AppDataManager @Inject
constructor(
    private val appApiHelper: AppApiHelper
) : DataManager {
    override suspend fun getSources(apiKey: String): Response<JsonElement> {
        return appApiHelper.getSources()
    }

    override suspend fun getNewsFromSource(apiKey: String, sources: String): Response<JsonElement> {
        return appApiHelper.getNewsFromSource(sources = sources)
    }

}