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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.model.Title
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.syncData()
    }

    TrendingTitlesContent(
        trendingTitles = uiState.trendingTitles
    )
}

@Composable
fun TrendingTitlesContent(
    modifier: Modifier = Modifier,
    trendingTitles: List<Title>
) {
    val pagerState = rememberPagerState(pageCount = { trendingTitles.size })

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
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) { page ->
            TrendingItemCard(
                title = trendingTitles[page],
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
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    // 포스터 비율 (2:3)에 맞춰 높이 계산
    val imageHeight = (screenWidth * 1.465F)

    Card(
        onClick = onClick,
        modifier = modifier
            .width(screenWidth - 64.dp)
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
                onState = {
                    when (it) {
                        AsyncImagePainter.State.Empty -> {
                            Logs.e("AsyncImage:onState:${it}")
                        }

                        is AsyncImagePainter.State.Error -> {
                            Logs.e("AsyncImage:onState:${it.result.throwable}")
                        }

                        is AsyncImagePainter.State.Loading -> {
                            Logs.e("AsyncImage:onState:${it}")
                        }

                        is AsyncImagePainter.State.Success -> {
                            Logs.e("AsyncImage:onState:${it.result}")
                        }
                    }
                }
            )

            // 반투명 그라데이션 오버레이
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

            // 미디어 타입 표시 배지
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