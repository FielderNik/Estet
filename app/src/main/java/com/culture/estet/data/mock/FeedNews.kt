package com.culture.estet.data.mock

import com.culture.estet.core.generateId
import com.culture.estet.data.feed.News
import com.culture.estet.domain.models.feed.NewsCategory

object FeedNews {

    val news1 = News(
        id = generateId(),
        title = "Всероссийский фестиваль",
        subtitle = "Балалайка сквозь века",
        date = "23 июня",
        category = NewsCategory.MUSIC,
        imageUrl = "https://thumb.tildacdn.com/tild3939-3863-4230-a662-366461343261/-/cover/720x860/center/center/-/format/webp/_WhatsApp_2023-05-26.jpg",
        newsUrl = "https://balakirev.arts.mos.ru/activity/festivals/school/vserossiyskiy-festival-balalayka-skvoz-veka/"
    )
    val news2 = News(
        id = generateId(),
        title = "Объединенный отчетный концерт Московских хореографических колледжей",
        subtitle = "Путь к вершине",
        date = "30 мая",
        category = NewsCategory.DANCE,
        imageUrl = "https://thumb.tildacdn.com/tild3561-3239-4733-b130-666230636566/-/resize/800x1000/-/format/webp/_WhatsApp_2023-05-23.jpg",
        newsUrl = "https://balakirev.arts.mos.ru/activity/festivals/school/vserossiyskiy-festival-balalayka-skvoz-veka/"
    )

    val news3 = News(
        id = generateId(),
        title = "Концерт длиною в жизнь",
        subtitle = "К столетию А.Г. Розума",
        date = "3 июня",
        category = NewsCategory.MUSIC,
        imageUrl = "https://thumb.tildacdn.com/tild3938-3961-4030-b863-373536306432/-/cover/600x860/center/center/-/format/webp/liam-mcgarry-4txHVae.jpg",
        newsUrl = "https://zilcc.ru/events/konczert-dlinoyu-v-zhizn-k-100-letiyu-narodnogo-artista-rsfsr-aleksandra-rozuma/"
    )
}
