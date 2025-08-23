package com.semin.watlism.feature.home

import com.semin.watlism.domain.model.Title

data class HomeUiState(
    val trendingTitles: List<Title>,
    val isLoading: Boolean,
    val isError: Boolean,
)
