package com.semin.watlism.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.model.Title
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

data class HomeUiState(
    val trendingTitles: List<Title>,
    val popularMovies: List<Movie>,
    val popularSeries: List<Series>,
    val popularDramas: List<Series>,
    val popularJapaneseAnimation: List<Series>,
    val isLoading: Boolean,
    val isError: Boolean,
)

val Title.categoryText: String
    @Composable get() = when (this) {
        is Movie -> stringResource(com.semin.watlism.feature.core.R.string.movie)
        is Series -> stringResource(com.semin.watlism.feature.core.R.string.series)
    }

val Title.containerColor: Color
    @Composable get() = when (this) {
        is Movie -> Color.Red.copy(alpha = 0.8f)
        is Series -> Color.Blue.copy(alpha = 0.8f)
    }

val YYYYMMDD_DOTS_LOCAL_DATE_FORMAT: DateTimeFormat<LocalDate>
    get() = LocalDate.Format {
        year()
        char('.')
        monthNumber()
        char('.')
        day()
    }
