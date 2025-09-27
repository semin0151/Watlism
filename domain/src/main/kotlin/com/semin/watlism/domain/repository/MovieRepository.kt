package com.semin.watlism.domain.repository

import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.model.MovieDetail
import com.semin.watlism.domain.value.TitleId
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(releaseDate: String): Flow<List<Movie>>

    fun getDetail(titleId: TitleId): Flow<MovieDetail>
}