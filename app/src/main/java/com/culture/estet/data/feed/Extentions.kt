package com.culture.estet.data.feed

import com.culture.estet.data.feed.local.NewsEntity
import com.culture.estet.data.feed.remote.NewsResponse
import com.culture.estet.core.generateId

fun NewsResponse.toNewsEntity(): NewsEntity {
    return NewsEntity(
        id = generateId(),
        title = title,
        subtitle = subtitle,
        date = date
    )
}

fun NewsResponse.toNews(): News {
    return News(
        title = title,
        subtitle = subtitle,
        date = date
    )
}

fun NewsEntity.toNews(): News {
    return News(
        title = title,
        subtitle = subtitle,
        date = date
    )
}

fun List<NewsResponse>.toNewsListEntity() = map(NewsResponse::toNewsEntity)
fun List<NewsResponse>.toNewsList() = map(NewsResponse::toNews)
fun List<NewsEntity>.toNewsList() = map(NewsEntity::toNews)