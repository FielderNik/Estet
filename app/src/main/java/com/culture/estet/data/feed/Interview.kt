package com.culture.estet.data.feed

data class Interview(
    override val id: String,
    override val title: String,
    val person: String,
    override val imageUrl: String,
    override val url: String,
): BaseNews(id, title, imageUrl, url)