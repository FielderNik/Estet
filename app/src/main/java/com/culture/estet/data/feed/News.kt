package com.culture.estet.data.feed

import com.culture.estet.domain.models.feed.NewsCategory

data class News(
    override val id: String,
    override val title: String,
    val subtitle: String?,
    val date: String?,
    override val category: NewsCategory,
    override val imageUrl: String,
    override val url: String,
) : BaseNews(id, title, imageUrl, url)


