package com.semin.watlism.feature.titledetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TitleDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TitleDetailViewModel = hiltViewModel()
) {
    Text("TitleDetail")
}