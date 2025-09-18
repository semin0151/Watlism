package com.semin.watlism.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semin.watlism.domain.provider.DateTimeProvider
import com.semin.watlism.domain.repository.GenresRepository
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
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleRepository: TitleRepository,
    private val movieRepository: MovieRepository,
    private val seriesRepository: SeriesRepository,
    private val genresRepository: GenresRepository,
    private val dateTimeProvider: DateTimeProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            true,
            false
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

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

        movieRepository.getPopularMovies(releaseDate = dateTimeProvider.getFirstOfYearYearsAgo(5))
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

        seriesRepository
            .getSeries(
                country = "KR",
                firstAirDateGte = dateTimeProvider.getFirstOfYearYearsAgo(5),
                withGenres = runBlocking { genresRepository.getGenreIdByName("드라마")?.id }
            ).onEach { popularDramas ->
                _uiState.update {
                    it.copy(
                        popularDramas = popularDramas,
                        isLoading = false
                    )
                }
            }
            .catch { Logs.e(it.message.toString()) }
            .launchIn(viewModelScope)

        seriesRepository
            .getSeries(
                country = "JP",
                firstAirDateGte = dateTimeProvider.getFirstOfYearYearsAgo(5),
                voteCountGte = 50.0,
                withGenres = runBlocking { genresRepository.getGenreIdByName("애니메이션")?.id },
            ).onEach { popularJapaneseAnimation ->
                _uiState.update {
                    it.copy(
                        popularJapaneseAnimation = popularJapaneseAnimation,
                        isLoading = false
                    )
                }
            }
            .catch { Logs.e(it.message.toString()) }
            .launchIn(viewModelScope)

        /**
         *
         *     /**
         *      * @Query("api_key") apiKey: String,
         *      *         @Query("with_origin_country") country: String = "JP",
         *      *         @Query("with_genres") genreId: Int = 16,
         *      *         @Query("sort_by") sortBy: String = "popularity.desc",
         *      *         @Query("first_air_date.gte") firstAirDateGte: String,
         *      *         @Query("vote_count.gte") voteCountGte: Int = 50,
         *      *         @Query("language") language: String = "ko-KR",
         *      *         @Query("page") page: Int = 1
         *      */
         */
    }
}