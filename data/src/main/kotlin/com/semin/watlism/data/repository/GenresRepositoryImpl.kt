package com.semin.watlism.data.repository

import com.semin.watlism.data.datasource.api.GenresApiDataSource
import com.semin.watlism.data.datasource.database.GenresDatabaseDataSource
import com.semin.watlism.domain.model.Genre
import com.semin.watlism.domain.repository.GenresRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val genresApiDataSource: GenresApiDataSource,
    private val genresDatabaseDataSource: GenresDatabaseDataSource
) : GenresRepository {
    override suspend fun getGenreIdByName(name: String): Genre? = supervisorScope {
        val localGenre = genresDatabaseDataSource.getGenreByName(name)

        if (localGenre == null) {
            val movieGenresDeferred = async { genresApiDataSource.getMovieGenres().getOrNull() }
            val tvGenresDeferred = async { genresApiDataSource.getTvGenres().getOrNull() }

            val (movieGenres, tvGenres) = awaitAll(movieGenresDeferred, tvGenresDeferred)
            movieGenres?.let { genresDatabaseDataSource.upsertGenres(it) }
            tvGenres?.let { genresDatabaseDataSource.upsertGenres(it) }

            movieGenres?.find { it.name == name }?.toGenre() ?: tvGenres?.find { it.name == name }?.toGenre()
        } else {
            localGenre.toGenre()
        }
    }
}