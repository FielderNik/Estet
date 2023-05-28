package com.culture.estet.data.mock

import com.culture.estet.core.generateBirthdayId
import com.culture.estet.core.generateId
import com.culture.estet.data.feed.Birthday
import com.culture.estet.data.feed.Interview
import com.culture.estet.data.feed.News
import com.culture.estet.domain.models.feed.NewsCategory

object FeedNews {

    private val IMAGE_SIZE = 100

    private val news1 = News(
        id = generateId(),
        title = "Всероссийский фестиваль",
        subtitle = "Балалайка сквозь века",
        date = "23 июня",
        category = NewsCategory.MUSIC,
        imageUrl = "https://thumb.tildacdn.com/tild3939-3863-4230-a662-366461343261/-/cover/720x860/center/center/-/format/webp/_WhatsApp_2023-05-26.jpg",
        url = "https://balakirev.arts.mos.ru/activity/festivals/school/vserossiyskiy-festival-balalayka-skvoz-veka/"
    )
    private val news2 = News(
        id = generateId(),
        title = "Объединенный отчетный концерт Московских хореографических колледжей",
        subtitle = "Путь к вершине",
        date = "30 мая",
        category = NewsCategory.DANCE,
        imageUrl = "https://thumb.tildacdn.com/tild3561-3239-4733-b130-666230636566/-/resize/800x1000/-/format/webp/_WhatsApp_2023-05-23.jpg",
        url = "https://balakirev.arts.mos.ru/activity/festivals/school/vserossiyskiy-festival-balalayka-skvoz-veka/"
    )

    private val news3 = News(
        id = generateId(),
        title = "Концерт длиною в жизнь",
        subtitle = "К столетию А.Г. Розума",
        date = "3 июня",
        category = NewsCategory.MUSIC,
        imageUrl = "https://thumb.tildacdn.com/tild3938-3961-4030-b863-373536306432/-/cover/600x860/center/center/-/format/webp/liam-mcgarry-4txHVae.jpg",
        url = "https://zilcc.ru/events/konczert-dlinoyu-v-zhizn-k-100-letiyu-narodnogo-artista-rsfsr-aleksandra-rozuma/"
    )

    private val news4 = News(
        id = generateId(),
        title = "Читаем вместе",
        subtitle = "Библионочь",
        date = "27 мая",
        category = NewsCategory.SEMINARS,
        imageUrl = "https://thumb.tildacdn.com/tild6630-3237-4764-b335-353763343461/-/cover/600x860/center/center/-/format/webp/photo.jpg",
        url = "https://night.bibliogorod.ru/"
    )

    private val news5 = News(
        id = generateId(),
        title = "Всероссийский фестиваль",
        subtitle = "Балалайка сквозь века",
        date = "23 июня",
        category = NewsCategory.MUSIC,
        imageUrl = "https://thumb.tildacdn.com/tild3939-3863-4230-a662-366461343261/-/cover/720x860/center/center/-/format/webp/_WhatsApp_2023-05-26.jpg",
        url = "https://balakirev.arts.mos.ru/activity/festivals/school/vserossiyskiy-festival-balalayka-skvoz-veka/"
    )

    private val news6 = News(
        id = generateId(),
        title = "Объединенный отчетный концерт Московских хореографических колледжей",
        subtitle = "Путь к вершине",
        date = "30 мая",
        category = NewsCategory.DANCE,
        imageUrl = "https://thumb.tildacdn.com/tild3561-3239-4733-b130-666230636566/-/resize/800x1000/-/format/webp/_WhatsApp_2023-05-23.jpg",
        url = "https://balakirev.arts.mos.ru/activity/festivals/school/vserossiyskiy-festival-balalayka-skvoz-veka/"
    )

    private val interview1 = Interview(
        id = generateId(),
        title = "Главное — любить музыку, много заниматься и стремиться продолжать великие традиции!",
        person = "Екатерина Ганелина",
        imageUrl = "https://thumb.tildacdn.com/tild3135-3136-4231-b734-353864643262/-/resize/800x1000/-/format/webp/DSCF0696.jpg",
        url = "https://metodcabinet.ru/ganelina"
    )

    private val interview2 = Interview(
        id = generateId(),
        title = "Каждое концертное выступление и обновление исполнительского репертуара открывает новые возможности для движения вперёд, ведь это беспрерывный интеллектуальный творческий поиск",
        person = "Наталья Осипова",
        imageUrl = "https://static.tildacdn.com/tild6565-3336-4532-b636-656339386362/image-26-08-22-10-51.jpeg",
        url = "https://metodcabinet.ru/osipova"
    )

    private val interview3 = Interview(
        id = generateId(),
        title = "Одним из важнейших факторов для творческого развития является профессиональный обмен и, может быть, не всегда простая, но конкурентная среда, когда ты и в собственной игре и в работе со студентами каждый раз должен держать высокую планку, что, конечно же, не всегда легко",
        person = "Константин Волостнов",
        imageUrl = "https://static.tildacdn.com/tild6639-6232-4264-a262-656661376431/IMG_5095.jpg",
        url = "https://metodcabinet.ru/volostnov"
    )

    private val interview4 = Interview(
        id = generateId(),
        title = "Под влиянием музыки раскрываются новые таланты и способности, развивается интеллект",
        person = "Марина Агафонова",
        imageUrl = "https://static.tildacdn.com/tild6438-3236-4165-a362-613232386232/___.jpg",
        url = "https://metodcabinet.ru/agafonova"
    )

    private val interview5 = Interview(
        id = generateId(),
        title = "Главное — любить музыку, много заниматься и стремиться продолжать великие традиции!",
        person = "Екатерина Ганелина",
        imageUrl = "https://thumb.tildacdn.com/tild3135-3136-4231-b734-353864643262/-/resize/800x1000/-/format/webp/DSCF0696.jpg",
        url = "https://metodcabinet.ru/ganelina"
    )

    private val interview6 = Interview(
        id = generateId(),
        title = "Каждое концертное выступление и обновление исполнительского репертуара открывает новые возможности для движения вперёд, ведь это беспрерывный интеллектуальный творческий поиск",
        person = "Наталья Осипова",
        imageUrl = "https://static.tildacdn.com/tild6565-3336-4532-b636-656339386362/image-26-08-22-10-51.jpeg",
        url = "https://metodcabinet.ru/osipova"
    )

    private val birthdayId1 = generateBirthdayId(1)
    private val birthday1 = Birthday(
        id = birthdayId1,
        title = "85 лет со дня рождени Высоцкого",
        imageUrl = "https://i.pravatar.cc/${IMAGE_SIZE}?u=${birthdayId1}"
    )

    private val birthdayId2 = generateBirthdayId(2)
    private val birthday2 = Birthday(
        id = birthdayId2,
        title = "школе искусств 50 лет",
        imageUrl = "https://i.pravatar.cc/${IMAGE_SIZE}?u=${birthdayId2}"
    )

    private val birthdayId3 = generateBirthdayId(3)
    private val birthday3 = Birthday(
        id = birthdayId3,
        title = "Рахманинов.150 лет со дня рождения",
        imageUrl = "https://i.pravatar.cc/${IMAGE_SIZE}?u=${birthdayId3}"
    )

    private val birthdayId4 = generateBirthdayId(4)
    private val birthday4 = Birthday(
        id = birthdayId4,
        title = "55 лет директору МШИ",
        imageUrl = "https://i.pravatar.cc/${IMAGE_SIZE}?u=${birthdayId4}"
    )

    private val birthdayId5 = generateBirthdayId(5)
    private val birthday5 = Birthday(
        id = birthdayId5,
        title = "95 лет. Татьяна Пилецкая",
        imageUrl = "https://i.pravatar.cc/${IMAGE_SIZE}?u=${birthdayId5}"
    )

    private val birthdayId6 = generateBirthdayId(6)
    private val birthday6 = Birthday(
        id = birthdayId6,
        title = "120 лет. Евгений Мравинский",
        imageUrl = "https://i.pravatar.cc/${IMAGE_SIZE}?u=${birthdayId6}"
    )

    private val birthdayId7 = generateBirthdayId(7)
    private val birthday7 = Birthday(
        id = birthdayId7,
        title = "175 лет. Поль Гоген",
        imageUrl = "https://i.pravatar.cc/${IMAGE_SIZE}?u=${birthdayId7}"
    )

    val newsList = listOf(news1, news2, news3, news4, news5, news6)
    val birthdaysList = listOf(birthday1, birthday2, birthday3, birthday4, birthday5, birthday6, birthday7)
    val interviewList = listOf(interview1, interview2, interview3, interview4, interview5, interview6)
}
