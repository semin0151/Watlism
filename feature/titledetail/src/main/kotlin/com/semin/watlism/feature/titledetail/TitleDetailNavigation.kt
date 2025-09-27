package com.semin.watlism.feature.titledetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.TitleType
import kotlinx.serialization.Serializable

@Serializable
data class TitleDetailRoute(
    val titleId: Long,
    val titleType: String,
)

fun NavController.navigateToTitleDetail(
    titleId: Long,
    titleType: String,
) = navigate(TitleDetailRoute(titleId = titleId, titleType = titleType))

fun NavGraphBuilder.titleDetailScreen() {
    composable<TitleDetailRoute> { backStackEntry ->
        val route = backStackEntry.toRoute<TitleDetailRoute>()
        val titleId = route.titleId
        val titleType = route.titleType
        TitleDetailScreen(
            titleId = TitleId.of(titleId),
            titleType = TitleType.valueOf(titleType)
        )
    }
}