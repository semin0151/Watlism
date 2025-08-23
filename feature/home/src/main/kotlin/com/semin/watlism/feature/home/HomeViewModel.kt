package com.semin.watlism.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semin.watlism.domain.repository.TitleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleRepository: TitleRepository,
) : ViewModel() {

    fun test() {
        titleRepository.getTrendingAll()
            .onStart { Logs.e("test:onStart") }
            .onCompletion { Logs.e("test:onCompletion") }
            .onEach { Logs.e("test:onEach:${it.joinToString("\n")}") }
            .catch { Logs.e("test:catch:$it") }
            .launchIn(viewModelScope)
    }
}