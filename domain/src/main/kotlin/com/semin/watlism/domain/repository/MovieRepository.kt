package com.semin.watlism.domain.repository

import com.semin.watlism.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(releaseDate: String): Flow<List<Movie>>
}