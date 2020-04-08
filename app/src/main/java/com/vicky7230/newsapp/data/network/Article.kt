package com.vicky7230.newsapp.data.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    var title: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String
) : Parcelable