package com.semin.watlism.data.database.datasource

import com.semin.watlism.data.database.dao.GenreDao
import com.semin.watlism.data.database.di.DatabaseDispatcher
import com.semin.watlism.data.database.entity.GenreEntity
import com.semin.watlism.data.datasource.database.GenresDatabaseDataSource
import com.semin.watlism.data.model.GenreData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenresDatabaseDataSourceImpl @Inject constructor(
    @DatabaseDispatcher private val dispatcher: CoroutineDispatcher,
    private val genreDao: GenreDao
) : GenresDatabaseDataSource {
    override suspend fun upsertGenres(genres: List<GenreData>) = withContext(dispatcher) {
        genreDao.upsertGenres(genres.map { GenreEntity.toGenreEntity(it) })
    }

    override suspend fun getGenres(): List<GenreData> = withContext(dispatcher) {
        genreDao.getAllGenres().map { it.toGenreData() }
    }

    override suspend fun getGenreById(id: Long): GenreData? = withContext(dispatcher) {
        genreDao.getGenreById(id)?.toGenreData()
    }

    override suspend fun getGenreByName(name: String): GenreData? = withContext(dispatcher) {
        genreDao.getGenreByName(name)?.toGenreData()
    }
}