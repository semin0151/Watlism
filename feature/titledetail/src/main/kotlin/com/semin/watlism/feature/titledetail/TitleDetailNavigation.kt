package com.semin.watlism.feature.titledetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.semin.watlism.domain.value.TitleId
import kotlinx.serialization.Serializable

@Serializable
data class TitleDetailRoute(
    val titleId: Long
)

fun NavController.navigateToTitleDetail(
    titleId: Long
) = navigate(TitleDetailRoute(titleId = titleId))

fun NavGraphBuilder.titleDetailScreen() {
    composable<TitleDetailRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<TitleDetailRoute>()
        val titleId = route.titleId
        TitleDetailScreen(
            titleId = TitleId.of(titleId)
        )
    }
}