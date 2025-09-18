package com.semin.watlism.feature.titledetail

import com.semin.watlism.domain.model.Title

data class TitleDetailUiState(
    val title: Title,
    val isLoading: Boolean,
    val isError: Boolean,
)