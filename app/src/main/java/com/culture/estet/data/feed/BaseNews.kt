package com.culture.estet.data.feed

import com.culture.estet.domain.models.feed.NewsCategory

open class BaseNews(
    open val id: String,
    open val title: String,
    open val imageUrl: String,
    open val url: String,
    open val category: NewsCategory? = null
)