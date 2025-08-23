package com.semin.watlism.feature.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.model.Title
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import java.util.Locale
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.syncData()
    }

    if (uiState.isError) {
        // todo set error ui
    } else if (uiState.isLoading) {
        // todo add loading indicator
    } else {
        TrendingTitlesContent(
            modifier = modifier,
            trendingTitles = uiState.trendingTitles
        )
    }
}

@Composable
fun TrendingTitlesContent(
    modifier: Modifier = Modifier,
    trendingTitles: List<Title>
) {
    // todo refactor
    val realCount = trendingTitles.size
    val startPosition = remember(realCount) {
        val mid = Int.MAX_VALUE.div(2)
        mid - (mid.mod(realCount))
    }
    val pagerState = rememberPagerState(
        initialPage = startPosition,
        pageCount = { Int.MAX_VALUE }
    )

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            // header
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 32.dp),
            beyondViewportPageCount = 1,
            key = { page -> trendingTitles[page.mod(realCount)].id.value }
        ) { page ->
            val pageOffset =
                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue.coerceIn(
                    0f,
                    1f
                )

            val scale = lerp(0.9f, 1f, 1f - pageOffset)
            val alpha = lerp(0.6f, 1f, 1f - pageOffset)

            // 가운데 카드가 z-order 위로 오게
            val z = lerp(0f, 1f, 1f - pageOffset)

            TrendingItemCard(
                modifier = Modifier
                    .zIndex(z)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    },
                title = trendingTitles[page.mod(realCount)],
                onClick = { },
            )
        }
    }
}

@Composable
fun TrendingItemCard(
    modifier: Modifier = Modifier,
    title: Title,
    onClick: () -> Unit,
) {
    // todo refactor
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val imageWidth = screenWidth.minus(32.dp)
    val imageHeight = (imageWidth * 1.465F)

    Card(
        onClick = onClick,
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
                    containerColor = when (title) {
                        is Movie -> {
                            Color.Red.copy(alpha = 0.8f)
                        }

                        is Series -> {
                            Color.Blue.copy(alpha = 0.8f)
                        }
                    }
                )
            ) {
                Text(
                    text = when (title) {
                        is Movie -> {
                            "영화"
                        }

                        is Series -> {
                            "시리즈"
                        }
                    },
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // 하단 텍스트 정보
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
                        text = "⭐ ${String.format(Locale.ROOT, "%.1f", title.rating.value)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    val YMD_DOTS = LocalDate.Format {
                        year()
                        char('.')
                        monthNumber()
                        char('.')
                        day()
                    }
                    Text(
                        text = title.createdAt.format(YMD_DOTS),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}