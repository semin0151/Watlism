package com.semin.watlism.feature.titledetail

import com.semin.watlism.domain.model.TitleDetail

data class TitleDetailUiState(
    val titleDetail: TitleDetail?,
    val isLoading: Boolean,
    val isError: Boolean,
)