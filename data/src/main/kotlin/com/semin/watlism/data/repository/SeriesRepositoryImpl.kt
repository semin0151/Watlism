package com.semin.watlism.data.repository

import com.semin.watlism.data.datasource.api.DiscoverApiDataSource
import com.semin.watlism.data.datasource.api.SeriesApiDataSource
import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.model.SeriesDetail
import com.semin.watlism.domain.repository.SeriesRepository
import com.semin.watlism.domain.value.TitleId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val discoverApiDataSource: DiscoverApiDataSource,
    private val seriesApiDataSource: SeriesApiDataSource
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

    override fun getSeries(
        country: String?,
        firstAirDateGte: String?,
        voteCountGte: Double?,
        withGenres: Long?,
        withoutGenres: List<Long>?,
    ): Flow<List<Series>> {
        return flow {
            discoverApiDataSource.getSeries(
                country,
                firstAirDateGte,
                voteCountGte,
                withGenres,
                withoutGenres
            ).run {
                if (isSuccess) {
                    emit(this.getOrNull()?.results?.map { it.toSeries() } ?: emptyList())
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }
    }

    override fun getDetail(titleId: TitleId): Flow<SeriesDetail> {
        return flow {
            seriesApiDataSource.getDetails(
                seriesId = titleId.value
            ).run {
                if (isSuccess) {
                    emit(this.getOrNull()?.toSeriesDetail() ?: throw IllegalStateException("SeriesDetail is null"))
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }
    }
}