package com.culture.estet.data.feed.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("title")
    val subtitle: String,

    @SerializedName("date")
    val date: String
): Serializable