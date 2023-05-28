package com.culture.estet.data.mock

import com.culture.estet.core.generateBirthdayId
import com.culture.estet.core.generateId
import com.culture.estet.data.feed.Birthday
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

    val news4 = News(
        id = generateId(),
        title = "Читаем вместе",
        subtitle = "Библионочь",
        date = "27 мая",
        category = NewsCategory.SEMINARS,
        imageUrl = "https://thumb.tildacdn.com/tild6630-3237-4764-b335-353763343461/-/cover/600x860/center/center/-/format/webp/photo.jpg",
        newsUrl = "https://night.bibliogorod.ru/"
    )

    val news5 = News(
        id = generateId(),
        title = "Главное — любить музыку, много заниматься и стремиться продолжать великие традиции!",
        subtitle = "Екатерина Ганелина",
        date = null,
        category = NewsCategory.INTERVIEW,
        imageUrl = "https://thumb.tildacdn.com/tild3135-3136-4231-b734-353864643262/-/resize/800x1000/-/format/webp/DSCF0696.jpg",
        newsUrl = "https://metodcabinet.ru/ganelina"
    )

    val news6 = News(
        id = generateId(),
        title = "Каждое концертное выступление и обновление исполнительского репертуара открывает новые возможности для движения вперёд, ведь это беспрерывный интеллектуальный творческий поиск",
        subtitle = "Наталья Осипова",
        date = null,
        category = NewsCategory.INTERVIEW,
        imageUrl = "https://static.tildacdn.com/tild6565-3336-4532-b636-656339386362/image-26-08-22-10-51.jpeg",
        newsUrl = "https://metodcabinet.ru/osipova"
    )

    val news7 = News(
        id = generateId(),
        title = "Одним из важнейших факторов для творческого развития является профессиональный обмен и, может быть, не всегда простая, но конкурентная среда, когда ты и в собственной игре и в работе со студентами каждый раз должен держать высокую планку, что, конечно же, не всегда легко",
        subtitle = "Константин Волостнов",
        date = null,
        category = NewsCategory.INTERVIEW,
        imageUrl = "https://static.tildacdn.com/tild6639-6232-4264-a262-656661376431/IMG_5095.jpg",
        newsUrl = "https://metodcabinet.ru/volostnov"
    )

    val news8 = News(
        id = generateId(),
        title = "Под влиянием музыки раскрываются новые таланты и способности, развивается интеллект",
        subtitle = "Марина Агафонова",
        date = null,
        category = NewsCategory.INTERVIEW,
        imageUrl = "https://static.tildacdn.com/tild6438-3236-4165-a362-613232386232/___.jpg",
        newsUrl = "https://metodcabinet.ru/agafonova"
    )

    private val birthdayId1 = generateBirthdayId(1)
    val birthday1 = Birthday(
        id = birthdayId1,
        title = "85 лет со дня рождени Высоцкого",
        imageUrl = "https://i.pravatar.cc/60?u=${birthdayId1}"
    )

    private val birthdayId2 = generateBirthdayId(2)
    val birthday2 = Birthday(
        id = birthdayId2,
        title = "школе искусств 50 лет",
        imageUrl = "https://i.pravatar.cc/60?u=${birthdayId2}"
    )

    private val birthdayId3 = generateBirthdayId(3)
    val birthday3 = Birthday(
        id = birthdayId3,
        title = "Рахманинов.150 лет со дня рождения",
        imageUrl = "https://i.pravatar.cc/60?u=${birthdayId3}"
    )

    private val birthdayId4 = generateBirthdayId(4)
    val birthday4 = Birthday(
        id = birthdayId4,
        title = "55 лет директору МШИ",
        imageUrl = "https://i.pravatar.cc/60?u=${birthdayId4}"
    )

    private val birthdayId5 = generateBirthdayId(5)
    val birthday5 = Birthday(
        id = birthdayId5,
        title = "95 лет. Татьяна Пилецкая",
        imageUrl = "https://i.pravatar.cc/60?u=${birthdayId5}"
    )

    private val birthdayId6 = generateBirthdayId(6)
    val birthday6 = Birthday(
        id = birthdayId6,
        title = "120 лет. Евгений Мравинский",
        imageUrl = "https://i.pravatar.cc/60?u=${birthdayId6}"
    )

    private val birthdayId7 = generateBirthdayId(7)
    val birthday7 = Birthday(
        id = birthdayId7,
        title = "175 лет. Поль Гоген",
        imageUrl = "https://i.pravatar.cc/60?u=${birthdayId7}"
    )
}
