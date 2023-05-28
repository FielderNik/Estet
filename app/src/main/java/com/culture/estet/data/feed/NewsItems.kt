package com.culture.estet.data.feed

sealed class NewsItems {
    data class NewsList(val newsList: List<News>) : NewsItems()
    data class BirthdaysList(val birthdaysList: List<Birthday>) : NewsItems()
    data class InterviewList(val interviewList: List<Interview>): NewsItems()
}