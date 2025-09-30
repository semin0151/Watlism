package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.model.MovieCreditsData
import com.semin.watlism.data.model.MovieDetailData

interface MovieApiDataSource {
    suspend fun getDetails(movieId: Long): Result<MovieDetailData>

    suspend fun getCredits(movieId: Long): Result<MovieCreditsData>
}