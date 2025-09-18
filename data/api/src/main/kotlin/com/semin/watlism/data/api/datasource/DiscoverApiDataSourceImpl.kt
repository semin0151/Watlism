package com.semin.watlism.data.api.datasource

import com.semin.watlism.data.api.TmdbDiscoverApi
import com.semin.watlism.data.api.config.SortBy
import com.semin.watlism.data.datasource.api.DiscoverApiDataSource
import com.semin.watlism.data.model.MovieResponseData
import com.semin.watlism.data.model.SeriesResponseData
import retrofit2.HttpException
import javax.inject.Inject

class DiscoverApiDataSourceImpl @Inject constructor(
    private val tmdbDiscoverApi: TmdbDiscoverApi,
) : DiscoverApiDataSource {
    override suspend fun getPopularMovies(
        primaryReleaseDate: String
    ): Result<MovieResponseData> {
        return tmdbDiscoverApi.getMovie(
            sortBy = SortBy.PopularityDesc.value,
            primaryReleaseDateGte = primaryReleaseDate,
        ).run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.toMovieResponseData())
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }

    override suspend fun getPopularSeries(): Result<SeriesResponseData> {
        return tmdbDiscoverApi.getTv(
            sortBy = SortBy.PopularityDesc.value,
        ).run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.toSeriesResponseData())
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }

    override suspend fun getSeries(
        country: String?,
        firstAirDateGte: String?,
        voteCountGte: Double?,
        withGenres: Long?,
        withoutGenres: List<Long>?,
    ) : Result<SeriesResponseData> {
        return tmdbDiscoverApi.getTv(
            sortBy = SortBy.PopularityDesc.value,
            withOriginCountry = country,
            firstAirDateGte = firstAirDateGte,
            voteCountGte = voteCountGte,
            withGenres = withGenres?.toString(),
            withoutGenres = withoutGenres?.toString()
        ).run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.toSeriesResponseData())
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }
}