package com.semin.watlism.feature.titledetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semin.watlism.domain.usecase.GetDetailUseCase
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.TitleType
import com.semin.watlism.feature.core.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class TitleDetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
): ViewModel() {
    fun test(
        titleId: TitleId,
        titleType: TitleType
    ) {
        getDetailUseCase(titleId, titleType)
            .onStart { Logs.e("onStart") }
            .onEach { Logs.e("onEach:${it}") }
            .onCompletion { Logs.e("onCompletion") }
            .catch { Logs.e("catch:${it}") }
            .launchIn(viewModelScope)
    }
}