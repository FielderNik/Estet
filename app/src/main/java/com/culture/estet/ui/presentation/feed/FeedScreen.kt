package com.culture.estet.ui.presentation.feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.culture.estet.R
import com.culture.estet.data.feed.Birthday
import com.culture.estet.data.feed.News
import com.culture.estet.data.feed.NewsItems
import com.culture.estet.domain.models.feed.NewsCategory
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState

@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {
    val appTopBarState = LocalAppTopBarState.current
    val state = viewModel.state.collectAsState()
    val title = stringResource(id = R.string.title_screen_feed)

    LaunchedEffect(Unit) {
        appTopBarState.title = title
    }

    LaunchedEffect(Unit) {
        viewModel.sendAction(FeedAction.Initialize)
    }

    FeedContent(state = state.value, sendAction = viewModel::sendAction)
}

@Composable
private fun FeedContent(state: FeedScreenState, sendAction: (FeedAction) -> Unit) {
    when (state) {
        is FeedScreenState.FeedFilled -> {
            FeedFilledScreen(
                selectedCategory = state.selectedCategory,
                newsCategoryList = state.newsCategoryList,
                newsList = state.newsList,
                sendAction = sendAction,
            )
        }
        FeedScreenState.Loading -> {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun FeedFilledScreen(
    selectedCategory: NewsCategory?,
    newsCategoryList: List<NewsItems>,
    newsList: List<News>,
    sendAction: (FeedAction) -> Unit,
) {
    if (selectedCategory == null) {
        UsualFeedFilledScreen(
            selectedCategory = selectedCategory,
            newsCategoryList = newsCategoryList,
            sendAction = sendAction,
        )
    } else {
        NewsFeedByCategory(
            selectedCategory = selectedCategory,
            newsList = newsList,
            sendAction = sendAction,
        )
    }
}

@Composable
private fun NewsFeedByCategory(
    selectedCategory: NewsCategory,
    newsList: List<News>,
    sendAction: (FeedAction) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            FilterBlock(selectedCategory = selectedCategory, sendAction = sendAction)
        }

        items(items = newsList, key = { item: News -> item.id }) { news ->
            NewsElement(news = news)
        }
    }
}

@Composable
private fun UsualFeedFilledScreen(
    selectedCategory: NewsCategory?,
    newsCategoryList: List<NewsItems>,
    sendAction: (FeedAction) -> Unit,
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            FilterBlock(selectedCategory = selectedCategory, sendAction = sendAction)
        }

        newsCategoryList.forEach { newsItem ->
            when (newsItem) {
                is NewsItems.BirthdaysList -> {
                    BirthdayItem(newsItem.birthdaysList)
                }
                is NewsItems.NewsList -> {
                    NewsItem(newsItem.newsList)
                }
                is NewsItems.TitleNews -> {
                    TitleNews(newsItem.title)
                }
            }
        }
    }
}


private fun LazyListScope.BirthdayItem(birthdaysList: List<Birthday>) {
    item {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items = birthdaysList, key = { item: Birthday -> item.id }) { birthday: Birthday ->
                BirthdayElement(birthday = birthday)
            }
        }
    }
}

private fun LazyListScope.NewsItem(newsList: List<News>) {
    items(items = newsList) { item: News ->
        NewsElement(news = item)
    }
}

private fun LazyListScope.TitleNews(title: String) {
    item {
        Text(text = title)
    }
}

@Composable
private fun NewsElement(news: News) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Column() {

            Text(text = news.title)

            news.subtitle?.also {
                Text(text = news.subtitle)
            }
        }
    }
}


@Composable
private fun BirthdayElement(birthday: Birthday) {
    Box(modifier = Modifier.size(64.dp)) {
        Column() {
            Icon(painter = painterResource(id = R.drawable.icon_profile), contentDescription = null, tint = Color.Magenta)
            Text(text = birthday.title, maxLines = 2, minLines = 2)
        }
    }

}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun FilterBlock(
    selectedCategory: NewsCategory?,
    sendAction: (FeedAction) -> Unit,
) {
    val filters = NewsCategory.values()
    FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)) {
        filters.forEachIndexed { index, category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = {
                    sendAction(FeedAction.ChangeCategory(category))
                },
                leadingIcon = if (selectedCategory == category) {
                    { Icon(painter = painterResource(id = R.drawable.icon_check), contentDescription = null) }
                } else {
                    null
                },
                label = {
                    Text(text = stringResource(id = category.labelSource()))
                }
            )
        }
    }
}