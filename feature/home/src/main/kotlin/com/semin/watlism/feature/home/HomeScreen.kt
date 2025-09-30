package com.semin.watlism.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.semin.watlism.domain.model.Title
import kotlinx.datetime.format
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onTitleClick: (Long, String) -> Unit,
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.syncData()
    }

    Column(modifier = modifier) {
        HomeHeader(
            onSearchClick = onSearchClick
        )

        if (uiState.isError) {
            // todo set error ui
        } else if (uiState.isLoading) {
            // todo add loading indicator
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    if (uiState.trendingTitles.isNotEmpty()) {
                        TrendingTitlesContent(
                            modifier = modifier,
                            trendingTitles = uiState.trendingTitles,
                            onTitleClick = onTitleClick
                        )
                    }
                }

                item {
                    if (uiState.popularMovies.isNotEmpty()) {
                        TitleSection(
                            sectionTitle = "인기 영화",
                            titles = uiState.popularMovies,
                            onTitleClick = onTitleClick
                        )
                    }
                }

                item {
                    if (uiState.popularSeries.isNotEmpty()) {
                        TitleSection(
                            sectionTitle = "인기 시리즈",
                            titles = uiState.popularSeries,
                            onTitleClick = onTitleClick
                        )
                    }
                }

                item {
                    if (uiState.popularDramas.isNotEmpty()) {
                        TitleSection(
                            sectionTitle = "인기 드라마",
                            titles = uiState.popularDramas,
                            onTitleClick = onTitleClick
                        )
                    }
                }

                item {
                    if (uiState.popularJapaneseAnimation.isNotEmpty()) {
                        TitleSection(
                            sectionTitle = "인기 애니메이션",
                            titles = uiState.popularJapaneseAnimation,
                            onTitleClick = onTitleClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrendingTitlesContent(
    modifier: Modifier = Modifier,
    trendingTitles: List<Title>,
    onTitleClick: (Long, String) -> Unit,
) {
    val pagerState = getInfinityPagerState(trendingTitles)

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 32.dp),
            beyondViewportPageCount = 1,
            key = { page -> trendingTitles[page.mod(trendingTitles.size)].id.value }
        ) { page ->
            LargeTitleCard(
                modifier = Modifier
                    .infinityPagerSideItem(
                        pagerState = pagerState,
                        page = page
                    ),
                title = trendingTitles[page.mod(trendingTitles.size)],
                onTitleClick = onTitleClick,
            )
        }
    }
}

@Composable
private fun getInfinityPagerState(
    lists: List<*>
): PagerState {
    val realCount = lists.size
    val mid = Int.MAX_VALUE.div(2)

    return rememberPagerState(
        initialPage = remember(realCount) { mid - (mid.mod(realCount)) },
        pageCount = { Int.MAX_VALUE }
    )
}

@Composable
private fun Modifier.infinityPagerSideItem(
    pagerState: PagerState,
    page: Int
): Modifier {
    val pageDistance = pagerState.currentPage - page
    val pageOffset = pageDistance.plus(pagerState.currentPageOffsetFraction).absoluteValue.coerceIn(0f, 1f)
    val scale = lerp(0.9f, 1f, 1f - pageOffset)
    val alpha = lerp(0.6f, 1f, 1f - pageOffset)
    val zIndex = lerp(0f, 1f, 1f - pageOffset)

    return this
        .zIndex(zIndex = zIndex)
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
            this.alpha = alpha
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeHeader(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { },
        actions = {
            IconButton(
                onClick = onSearchClick
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "검색"
                )
            }
        }
    )
}

@Composable
private fun LargeTitleCard(
    modifier: Modifier = Modifier,
    title: Title,
    onTitleClick: (Long, String) -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageWidth = screenWidth.minus(32.dp)
    val imageHeight = (imageWidth * 1.465F)

    Card(
        onClick = {
            onTitleClick.invoke(title.id.value, title.type.name)
        },
        modifier = modifier
            .width(imageWidth)
            .height(imageHeight)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(title.posterUrl.value)
                    .crossfade(true)
                    .build(),
                contentDescription = title.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )

            Card(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = title.containerColor
                )
            ) {
                Text(
                    text = title.categoryText,
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(com.semin.watlism.feature.core.R.string.star_rate, title.rating.value),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = title.createdAt.format(YYYYMMDD_DOTS_LOCAL_DATE_FORMAT),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun TitleSection(
    sectionTitle: String,
    titles: List<Title>,
    onTitleClick: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp).padding(horizontal = 16.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(titles) { title ->
                TitleItemCard(
                    title = title,
                    onClick = { onTitleClick(title.id.value, title.type.name) }
                )
            }
        }
    }
}

@Composable
fun TitleItemCard(
    title: Title,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.width(120.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(title.posterUrl.value)
                    .crossfade(true)
                    .build(),
                contentDescription = title.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
            )
        }
    }
}