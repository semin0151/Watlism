package com.semin.watlism.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semin.watlism.domain.repository.TitleRepository
import com.semin.watlism.feature.core.Logs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleRepository: TitleRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(emptyList(), true, false))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun syncData() {
        titleRepository.getTrendingAll()
            .onStart { Logs.e("test:onStart") }
            .onCompletion { Logs.e("test:onCompletion") }
            .onEach { trendingTitles ->
                Logs.e("test:onEach:${trendingTitles.joinToString("\n")}")
                _uiState.update {
                    it.copy(
                        trendingTitles = trendingTitles,
                        isLoading = false
                    )
                }
            }
            .catch { Logs.e("test:catch:$it") }
            .launchIn(viewModelScope)
    }
}