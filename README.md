# 📄Документация

Ссылка на apk-файл: [https://disk.yandex.ru/d/v31jW9kKawbwcA](https://disk.yandex.ru/d/-UNrGc1HPoXosQ)

Репозиторий сервера: https://github.com/FielderNik/estet_server

## Стек
* Jetpack Compose
* Dagger Hilt
* Flow
* Сoroutines
* Room
* Retrofit 2
* MapKit

## Экраны

Доступ к основным категориям приложения осуществляется с помощью BottomBar-а, в котором представлено 5 картегорий: задания, соревнования, карта со школами искусств, курсы и лента новостей

![image](https://github.com/FielderNik/Estet/assets/55051470/9f08c6b5-9998-45dc-b7eb-bc4bf709ec76)

Также имеется вход в личный кабинет, где содержится вся информация о пользователе

![image](https://github.com/FielderNik/Estet/assets/55051470/cb35d264-e5ae-4101-a7cd-90a6f7cecf27)

### Задания

Во вкладке _Задания_ можно выбрать одну из интересующих категорий: живопись, танцы, театр, музыка
Выбрав интересующую категорию, предлагается три уровня сложности: новичок, продвинутый и эксперт

![image](https://github.com/FielderNik/Estet/assets/55051470/e60dbb3a-427c-4d55-9c46-8de663b5bb42)

### Соревнование

В этой вкладке вам доступен рейтинг друзей. Есть возможность сортировать рейтинг по категориям

![image](https://github.com/FielderNik/Estet/assets/55051470/b48ca78e-3384-4038-baf8-49176b27c9c3)


### Карта школ искусств

На этой карте можно увидеть где находятся школы, а также посмотреть подробную информацию о каждой, кликнув на ее пин

![image](https://github.com/FielderNik/Estet/assets/55051470/732a7f9d-0cc2-4fb6-9ef0-fce84b0d5713) ![image](https://github.com/FielderNik/Estet/assets/55051470/337b530a-a365-4a03-b7db-203de333fb46)

### Лента

В ленте вам будет доступно много разного контента, который можно будет сортировать по категориям

![image](https://github.com/FielderNik/Estet/assets/55051470/dd0ada82-a55d-403a-83f3-dc3ed00e7ecd)

Также, по клику на событие, будет открываться WebView, где можно будет подробнее ознакомиться с данным мероприятием

## Темы
Приложение поддерживает как светлую, так и темную темы

![image](https://github.com/FielderNik/Estet/assets/55051470/81391408-913c-4618-8b6d-834d9a25ceee) ![image](https://github.com/FielderNik/Estet/assets/55051470/f3dbcbee-4d28-400b-8a3b-e1e1269eb933)




## 💡Примечание

Для реализации карты со школами было использовано api MapKit-а. Чтобы продемонстрировать работоспособность был задействован api-ключ для lite версии, который станет недействительным после завершения хакатона. Если требуется в дальнейшем использовать функционал MapKit, рекомендуется приобрести ключ в [кабинете разработчика](https://developer.tech.yandex.ru/) и заменить его в файле . Также можно использовать full-версию данного апи, если функционала lite-версии будет недостаточно. Как это сделать можно прочитать в [документации](https://yandex.ru/dev/maps/mapkit/doc/android-quickstart/concepts/android/quickstart.html)
