package com.semin.watlism.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.semin.watlism.feature.home.navigation.HomeRoute
import com.semin.watlism.feature.home.navigation.homeScreen

@Composable
fun WatlismNavHost(
    navigator: WatlismNavigator,
    modifier: Modifier = Modifier,
) {
    val navigationController = navigator.navigatorController

    NavHost(
        navController = navigationController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        homeScreen()
    }
}