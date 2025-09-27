package com.semin.watlism.feature.titledetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.feature.core.Logs

@Composable
fun TitleDetailScreen(
    modifier: Modifier = Modifier,
    titleId: TitleId,
    viewModel: TitleDetailViewModel = hiltViewModel()
) {
    Logs.e("titleId: $titleId")
    Text("TitleDetail")
}