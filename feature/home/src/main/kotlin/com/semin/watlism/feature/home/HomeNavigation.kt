package com.semin.watlism.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavController.navigateToHome() = navigate(HomeRoute)

fun NavGraphBuilder.homeScreen(
    onTitleClick: (Long, String) -> Unit,
    onSearchClick: () -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            onTitleClick = onTitleClick,
            onSearchClick = onSearchClick
        )
    }
}