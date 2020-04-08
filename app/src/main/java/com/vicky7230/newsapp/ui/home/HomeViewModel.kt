package com.vicky7230.newsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.vicky7230.newsapp.data.DataManager
import com.vicky7230.newsapp.data.network.Resource
import com.vicky7230.newsapp.data.network.Source
import com.vicky7230.newsapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {

    val resource = MutableLiveData<Resource<MutableList<Source>>>()

    fun getSources() {
        viewModelScope.launch {
            resource.value = Resource.Loading

            val response = safeApiCall { dataManager.getSources() }

            when (response) {
                is Resource.Success -> {
                    val jsonObject = response.data.asJsonObject
                    if (jsonObject["status"].asString == "ok") {
                        val sourcesFromNetwork: MutableList<Source> = arrayListOf()
                        val sourcesJsonArray = jsonObject["sources"].asJsonArray
                        sourcesJsonArray
                            .forEach { sourceJsonElement: JsonElement ->
                                sourcesFromNetwork.add(
                                    Source(
                                        sourceJsonElement.asJsonObject["id"].asString,
                                        sourceJsonElement.asJsonObject["name"].asString,
                                        sourceJsonElement.asJsonObject["description"].asString,
                                        sourceJsonElement.asJsonObject["url"].asString,
                                        sourceJsonElement.asJsonObject["category"].asString,
                                        sourceJsonElement.asJsonObject["language"].asString,
                                        sourceJsonElement.asJsonObject["country"].asString
                                    )
                                )
                            }

                        resource.value =
                            Resource.Success(sourcesFromNetwork.take(10).toMutableList())
                    } else {
                        Resource.Error(IOException(jsonObject.get("message").asString))
                    }
                }
                is Resource.Error -> {
                    resource.value = response
                }
            }
        }
    }

}