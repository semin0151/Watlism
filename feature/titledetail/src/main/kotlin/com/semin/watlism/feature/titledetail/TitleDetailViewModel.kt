package com.semin.watlism.feature.titledetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semin.watlism.domain.usecase.GetDetailUseCase
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.TitleType
import com.semin.watlism.feature.core.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TitleDetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        TitleDetailUiState(
            titleDetail = null,
            isLoading = true,
            isError = false
        )
    )
    val uiState: StateFlow<TitleDetailUiState> = _uiState.asStateFlow()

    fun syncData(
        titleId: TitleId,
        titleType: TitleType
    ) {
        Logs.e("syncData: $titleId, $titleType")
        getDetailUseCase(titleId, titleType)
            .onEach { titleDetail ->
                Logs.e("titleDetail:$titleDetail")
                _uiState.update { it.copy(titleDetail = titleDetail) }
            }
            .catch { Logs.e("catch:${it}") }
            .launchIn(viewModelScope)
    }
}