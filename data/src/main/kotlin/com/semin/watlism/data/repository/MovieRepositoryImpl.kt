package com.semin.watlism.data.repository

import com.semin.watlism.data.datasource.api.DiscoverApiDataSource
import com.semin.watlism.data.datasource.api.MovieApiDataSource
import com.semin.watlism.domain.model.Credits
import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.model.MovieDetail
import com.semin.watlism.domain.repository.MovieRepository
import com.semin.watlism.domain.value.TitleId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val discoverApiDataSource: DiscoverApiDataSource,
    private val movieApiDataSource: MovieApiDataSource
) : MovieRepository {
    override fun getPopularMovies(releaseDate: String): Flow<List<Movie>> {
        return flow {
            discoverApiDataSource.getPopularMovies(
                primaryReleaseDate = releaseDate
            ).run {
                if (isSuccess) {
                    emit(this.getOrNull()?.results?.map { it.toMovie() } ?: emptyList())
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }


    }

    override fun getDetail(titleId: TitleId): Flow<MovieDetail> {
        return flow {
            movieApiDataSource.getDetails(
                movieId = titleId.value
            ).run {
                if (isSuccess) {
                    emit(this.getOrNull()?.toMovieDetail() ?: throw IllegalStateException("MovieDetail is null"))
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }
    }

    override fun getCredits(titleId: TitleId): Flow<Credits> {
        return flow {
            movieApiDataSource.getCredits(
                movieId = titleId.value
            ).run {
                if (isSuccess) {
                    emit(this.getOrNull()?.toCredits(titleId) ?: throw IllegalStateException("Credits is null"))
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }
    }
}