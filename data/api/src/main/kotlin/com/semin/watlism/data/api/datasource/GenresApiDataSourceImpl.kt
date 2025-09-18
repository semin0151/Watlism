package com.semin.watlism.data.api.datasource

import com.semin.watlism.data.api.TmdbGenresApi
import com.semin.watlism.data.datasource.api.GenresApiDataSource
import com.semin.watlism.data.model.GenreData
import retrofit2.HttpException
import javax.inject.Inject

class GenresApiDataSourceImpl @Inject constructor(
    private val tmdbGenresApi: TmdbGenresApi
) : GenresApiDataSource {

    override suspend fun getMovieGenres(): Result<List<GenreData>> {
        return tmdbGenresApi.getMovieGenres().run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.genres.map { it.toGenreData() })
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }

    override suspend fun getTvGenres(): Result<List<GenreData>> {
        return tmdbGenresApi.getTvGenres().run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.genres.map { it.toGenreData() })
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }
}