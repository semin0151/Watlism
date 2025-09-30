package com.semin.watlism.feature.titledetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.semin.watlism.domain.model.Genre
import com.semin.watlism.domain.model.MovieDetail
import com.semin.watlism.domain.model.SeriesDetail
import com.semin.watlism.domain.model.TitleDetail
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.TitleType
import java.util.Locale

/**
 * todo
 * - set crew, cast
 * - set similar contents
 */
@Composable
fun TitleDetailScreen(
    modifier: Modifier = Modifier,
    titleId: TitleId,
    titleType: TitleType,
    viewModel: TitleDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.syncData(titleId, titleType)
    }

    uiState.titleDetail?.let {
        TitleDetailContent(
            modifier = modifier,
            titleDetail = it
        )
    }
}

@Composable
private fun TitleDetailContent(
    modifier: Modifier,
    titleDetail: TitleDetail,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(titleDetail.backdropUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = titleDetail.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            )
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(titleDetail.posterUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = titleDetail.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(80.dp)
                        .height(120.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = titleDetail.name,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val genreNames = titleDetail.genres
                        items(genreNames.take(3)) { genreName ->
                            GenreChip(genreName)
                        }
                    }

                    Text(
                        text = when (titleDetail) {
                            is MovieDetail -> titleDetail.releaseDate
                            is SeriesDetail -> titleDetail.firstAirDate
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.8f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = "평점",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${
                                String.format(
                                    Locale.ROOT,
                                    "%.1f",
                                    titleDetail.voteAverage
                                )
                            } (${
                                String.format(
                                    Locale.getDefault(),
                                    "%,d",
                                    titleDetail.voteCount
                                )
                            })",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (titleDetail.overview.isNotEmpty()) {
                DetailSection(
                    title = "개요",
                    content = {
                        var isExpanded by remember { mutableStateOf(false) }

                        Text(
                            text = titleDetail.overview,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                            overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                            onTextLayout = { textLayoutResult -> }
                        )

                        TextButton(
                            onClick = { isExpanded = !isExpanded }
                        ) {
                            Text(if (isExpanded) "접기" else "더보기")
                        }
                    }
                )
            }

            DetailSection(
                title = "상세 정보",
                content = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DetailInfoRow("원제", titleDetail.originalName)
                        DetailInfoRow("언어", titleDetail.originalLanguage.uppercase())

                        if (titleDetail is SeriesDetail) {
                            DetailInfoRow("시즌", "${titleDetail.numberOfSeasons}시즌")
                            DetailInfoRow("에피소드", "총 ${titleDetail.numberOfEpisodes}화")
                        }

                        when (titleDetail) {
                            is SeriesDetail -> DetailInfoRow("첫 방영일", titleDetail.firstAirDate)
                            is MovieDetail -> DetailInfoRow("개봉일", titleDetail.releaseDate)
                        }

                        DetailInfoRow(
                            "평점",
                            "${
                                String.format(
                                    Locale.ROOT,
                                    "%.1f",
                                    titleDetail.voteAverage
                                )
                            } (${String.format(Locale.getDefault(), "%,d", titleDetail.voteCount)})"
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun GenreChip(
    genre: Genre,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Text(
            text = genre.name,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun DetailSection(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            content()
        }
    }
}

@Composable
private fun DetailInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(2f)
        )
    }
}