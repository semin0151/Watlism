package com.semin.watlism.data.repository

import com.semin.watlism.data.datasource.api.DiscoverApiDataSource
import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val discoverApiDataSource: DiscoverApiDataSource,
) : MovieRepository {
    override fun getPopularMovies(releaseYear: Int): Flow<List<Movie>> {
        return flow {
            discoverApiDataSource.getPopularMovies(
                primaryReleaseYear = releaseYear
            ).run {
                if (isSuccess) {
                    emit(this.getOrNull()?.results?.map { it.toMovie() } ?: emptyList())
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }
    }
}