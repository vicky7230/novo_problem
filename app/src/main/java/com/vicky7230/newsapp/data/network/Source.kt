package com.vicky7230.newsapp.data.network

data class Source(
    var id: String,
    var name: String,
    var description: String,
    var url: String,
    var category: String,
    var language: String,
    var country: String
)