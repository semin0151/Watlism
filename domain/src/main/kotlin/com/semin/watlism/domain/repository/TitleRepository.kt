package com.semin.watlism.domain.repository

import com.semin.watlism.domain.model.Title
import kotlinx.coroutines.flow.Flow

interface TitleRepository {
    fun getTrendingAll(): Flow<List<Title>>
}