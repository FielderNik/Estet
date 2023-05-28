package com.culture.estet.data.feed.remote

import com.culture.estet.core.generateId
import com.culture.estet.data.feed.News
import com.culture.estet.data.feed.local.NewsEntity
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

fun NewsResponse.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = generateId(),
        title = title,
        subtitle = subtitle,
        date = date
    )
}

//fun NewsResponse.toNews(): News {
//    return News(
//        id = generateId(),
//        title = title,
//        subtitle = subtitle,
//        date = date
//    )
//}
fun List<NewsResponse>.toNewsListEntity() = map(NewsResponse::toNewsEntity)
//fun List<NewsResponse>.toNewsList() = map(NewsResponse::toNews)