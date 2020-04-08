package com.vicky7230.newsapp.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonElement
import com.vicky7230.newsapp.data.DataManager
import com.vicky7230.newsapp.data.network.Article
import com.vicky7230.newsapp.data.network.Resource
import com.vicky7230.newsapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val dataManager: DataManager
) : BaseViewModel() {


    val news = MutableLiveData<Resource<MutableList<Article>>>()

    fun getNews(source: String?) {
        viewModelScope.launch {
            val response = safeApiCall { dataManager.getNewsFromSource(sources = source!!) }

            when (response) {
                is Resource.Success -> {
                    val jsonObject = response.data.asJsonObject
                    if (jsonObject["status"].asString == "ok") {
                        val sourcesFromNetwork: MutableList<Article> = arrayListOf()
                        val newsJsonArray = jsonObject["articles"].asJsonArray
                        newsJsonArray
                            .forEach { newsJsonElement: JsonElement ->
                                sourcesFromNetwork.add(
                                    Article(
                                        newsJsonElement.asJsonObject["title"].asString,
                                        newsJsonElement.asJsonObject["url"].asString,
                                        newsJsonElement.asJsonObject["urlToImage"].asString,
                                        newsJsonElement.asJsonObject["publishedAt"].asString
                                    )
                                )
                            }

                        news.value =
                            Resource.Success(sourcesFromNetwork.take(10).toMutableList())
                    } else {
                        Resource.Error(IOException(jsonObject.get("message").asString))
                    }
                }
                is Resource.Error -> {
                    news.value = response
                }
            }
        }
    }

}