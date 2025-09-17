package com.semin.watlism.data.repository

import com.semin.watlism.data.datasource.api.DiscoverApiDataSource
import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val discoverApiDataSource: DiscoverApiDataSource
) : SeriesRepository {
    override fun getPopularSeries(): Flow<List<Series>> {
        return flow {
            discoverApiDataSource.getPopularSeries().run {
                if (isSuccess) {
                    emit(this.getOrNull()?.results?.map { it.toSeries() } ?: emptyList())
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }
    }
}