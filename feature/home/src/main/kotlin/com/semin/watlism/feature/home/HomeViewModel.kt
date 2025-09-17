package com.semin.watlism.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semin.watlism.domain.repository.MovieRepository
import com.semin.watlism.domain.repository.SeriesRepository
import com.semin.watlism.domain.repository.TitleRepository
import com.semin.watlism.feature.core.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleRepository: TitleRepository,
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            emptyList(),
            emptyList(),
            emptyList(),
            true,
            false
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    @OptIn(ExperimentalTime::class)
    private val now: LocalDateTime get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    private val startOfFiveYearsAgo: String get() = "${now.year.minus(5)}-01-01"

    @OptIn(ExperimentalTime::class)
    fun syncData() {
        titleRepository.getTrendingAll()
            .onEach { trendingTitles ->
                _uiState.update {
                    it.copy(
                        trendingTitles = trendingTitles,
                        isLoading = false
                    )
                }
            }
            .catch { Logs.e(it.message.toString()) }
            .launchIn(viewModelScope)

        movieRepository.getPopularMovies(releaseDate = startOfFiveYearsAgo)
            .onEach { popularMovies ->
                _uiState.update {
                    it.copy(
                        popularMovies = popularMovies,
                        isLoading = false
                    )
                }
            }
            .catch { Logs.e(it.message.toString()) }
            .launchIn(viewModelScope)

        seriesRepository.getPopularSeries()
            .onEach { popularSeries ->
                _uiState.update {
                    it.copy(
                        popularSeries = popularSeries,
                        isLoading = false
                    )
                }
            }
            .catch { Logs.e(it.message.toString()) }
            .launchIn(viewModelScope)
    }
}