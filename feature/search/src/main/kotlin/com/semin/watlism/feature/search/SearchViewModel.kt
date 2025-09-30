package com.semin.watlism.feature.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(
        SearchUiState(
            query = "",
            isLoading = true
        )
    )
    val uiState = _uiState.asStateFlow()

    fun typing(query: String) {
        _uiState.update { it.copy(query = query) }
    }
}