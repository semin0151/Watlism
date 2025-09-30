package com.semin.watlism.data.api.datasource

import com.semin.watlism.data.api.TmdbMovieApi
import com.semin.watlism.data.datasource.api.MovieApiDataSource
import com.semin.watlism.data.model.CreditsData
import com.semin.watlism.data.model.MovieDetailData
import retrofit2.HttpException
import javax.inject.Inject

class MovieApiDataSourceImpl @Inject constructor(
    private val tmdbMovieApi: TmdbMovieApi
) : MovieApiDataSource {
    override suspend fun getDetails(movieId: Long): Result<MovieDetailData> {
        return tmdbMovieApi.getDetails(movieId = movieId).run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.toMovieDetailData())
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }

    override suspend fun getCredits(movieId: Long): Result<List<CreditsData>> {
        return tmdbMovieApi.getCredits(movieId = movieId).run {
            if(isSuccessful) {
                val body = this.body()

                if(body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.cast.map { it.toCreditData() })
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }
}