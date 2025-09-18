package com.semin.watlism.feature.titledetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class TitleDetailRoute(
    val titleId: Long
)

fun NavController.navigateToTitleDetail(
    titleId: Long
) = navigate(TitleDetailRoute(titleId = titleId))

fun NavGraphBuilder.titleDetailScreen() {
    composable<TitleDetailRoute> {
        TitleDetailScreen()
    }
}