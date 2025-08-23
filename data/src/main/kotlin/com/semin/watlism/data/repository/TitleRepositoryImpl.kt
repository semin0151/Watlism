package com.semin.watlism.data.repository

import com.semin.watlism.data.datasource.api.TrendingApiDataSource
import com.semin.watlism.domain.model.Title
import com.semin.watlism.domain.repository.TitleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TitleRepositoryImpl @Inject constructor(
    private val trendingApiDataSource: TrendingApiDataSource
) : TitleRepository {
    override fun getTrendingAll(): Flow<List<Title>> {
        return flow {
            trendingApiDataSource.getAll().run {
                if (isSuccess) {
                   emit(this.getOrNull()?.results?.map { it.toTitle() } ?: emptyList())
                } else {
                    exceptionOrNull()?.let { throw it }
                }
            }
        }

    }
}