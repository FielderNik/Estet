package com.culture.estet.ui.presentation.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.culture.estet.R
import com.culture.estet.data.feed.*
import com.culture.estet.domain.models.feed.NewsCategory
import com.culture.estet.ui.presentation.localcomposition.LocalAppScreenState
import com.culture.estet.ui.presentation.localcomposition.LocalAppTopBarState
import com.culture.estet.ui.presentation.navigation.webview.WebViewDestination
import com.culture.estet.ui.theme.LightPastelPurple

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
            UsualFeedFilledScreen(
                selectedCategory = state.selectedCategory,
                newsCategoryList = state.newsCategoryList,
                sendAction = sendAction,
            )
        }
        FeedScreenState.Loading -> {
            CircularProgressIndicator()
        }
        is FeedScreenState.FilteredNews -> {
            NewsFeedByCategory(
                selectedCategory = state.selectedCategory,
                filteredList = state.newsCategoryList,
                sendAction = sendAction,
            )
        }
    }
}

@Composable
private fun NewsFeedByCategory(
    selectedCategory: NewsCategory,
    filteredList: List<BaseNews>,
    sendAction: (FeedAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            FilterBlock(selectedCategory = selectedCategory, sendAction = sendAction)
        }

        items(items = filteredList, key = { item -> item.id }) { item ->
            FilteredNewsElement(news = item)

        }
    }
}

@Composable
private fun UsualFeedFilledScreen(
    selectedCategory: NewsCategory?,
    newsCategoryList: List<NewsItems>,
    sendAction: (FeedAction) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
    ) {
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
                is NewsItems.InterviewList -> {
                    Interview(newsItem.interviewList)
                }
            }
        }
    }
}


private fun LazyListScope.BirthdayItem(birthdaysList: List<Birthday>) {
    item {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp, start = 8.dp),
            text = stringResource(id = R.string.title_news_feed_birthdays),
            fontWeight = FontWeight.Bold
        )
    }
    item {
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(items = birthdaysList, key = { item: Birthday -> item.id }) { birthday: Birthday ->
                BirthdayElement(birthday = birthday)
            }
        }
    }
}

private fun LazyListScope.NewsItem(newsList: List<News>) {
    item {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp, start = 8.dp),
            text = stringResource(id = R.string.title_news_feed_announces),
            fontWeight = FontWeight.Bold
        )
    }
    itemsIndexed(items = newsList, key = { index, item: News -> item.id }) { index, item ->
        NewsElement(news = item)

        if (index < newsList.lastIndex) {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun LazyListScope.Interview(interviewList: List<Interview>) {
    item {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp, start = 8.dp),
            text = stringResource(id = R.string.title_news_feed_interview),
            fontWeight = FontWeight.Bold
        )
    }
    item {
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = interviewList, key = { item: Interview -> item.id }) { interview: Interview ->
                InterviewElement(interview)
            }
        }
    }
}

@Composable
private fun InterviewElement(interview: Interview) {
    val navigator = LocalAppScreenState.current.navController

    Column(
        modifier = Modifier
            .width(160.dp)
            .clickable {
                val route = WebViewDestination.navigationRoute(interview.url)
                navigator.navigate(route)
            }
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .width(180.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current).data(interview.imageUrl).crossfade(true).build(),
            contentDescription = null,
            loading = {
                CircularProgressIndicator(color = Color.Gray, strokeCap = StrokeCap.Round, strokeWidth = 2.dp)
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                )
            },
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = interview.person,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = interview.title,
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Light
        )

    }
}

@Composable
private fun NewsElement(news: News) {
    val navigator = LocalAppScreenState.current.navController

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                val route = WebViewDestination.navigationRoute(news.url)
                navigator.navigate(route)
            }
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current).data(news.imageUrl).crossfade(true).build(),
            contentDescription = null,
            loading = {
                CircularProgressIndicator(color = Color.Gray, strokeCap = StrokeCap.Round, strokeWidth = 2.dp)
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                )
            },
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = SolidColor(Color.White), shape = RoundedCornerShape(8.dp), alpha = 0.85f)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {

                Text(
                    text = news.title,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            news.subtitle?.also {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(brush = SolidColor(Color.White), shape = RoundedCornerShape(8.dp), alpha = 0.9f)
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {

                    Text(text = news.subtitle, maxLines = 1)
                }
            }

            news.date?.also {
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(LightPastelPurple, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = news.date, color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}


@Composable
private fun FilteredNewsElement(news: BaseNews) {
    val navigator = LocalAppScreenState.current.navController
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                val route = WebViewDestination.navigationRoute(news.url)
                navigator.navigate(route)
            }
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current).data(news.imageUrl).crossfade(true).build(),
            contentDescription = null,
            loading = {
                CircularProgressIndicator(color = Color.Gray, strokeCap = StrokeCap.Round, strokeWidth = 2.dp)
            },
            error = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                )
            },
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(brush = SolidColor(Color.White), shape = RoundedCornerShape(8.dp), alpha = 0.85f)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {

                Text(
                    text = news.title,
                    maxLines = 2,
                    minLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
private fun BirthdayElement(birthday: Birthday) {
    Box(modifier = Modifier.width(100.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current).data(birthday.imageUrl).crossfade(true).build(),
                contentDescription = null,
                loading = {
                    CircularProgressIndicator(color = Color.Gray, strokeCap = StrokeCap.Round, strokeWidth = 2.dp)
                },
                error = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray), contentAlignment = Alignment.Center
                    ) {
                        Icon(painter = painterResource(id = R.drawable.icon_profile), contentDescription = null, tint = Color.White)
                    }
                },
                contentScale = ContentScale.Crop,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = birthday.title,
                maxLines = 2,
                minLines = 2,
                fontSize = 10.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 14.sp,
                textAlign = TextAlign.Center
            )
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
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
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