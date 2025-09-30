package com.semin.watlism.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.semin.watlism.feature.home.HomeRoute
import com.semin.watlism.feature.home.homeScreen
import com.semin.watlism.feature.search.navigateToSearch
import com.semin.watlism.feature.search.searchScreen
import com.semin.watlism.feature.titledetail.navigateToTitleDetail
import com.semin.watlism.feature.titledetail.titleDetailScreen

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
        homeScreen(
            onTitleClick = { titleId, titleType ->
                navigationController.navigateToTitleDetail(titleId, titleType)
            },
            onSearchClick = {
                navigationController.navigateToSearch()
            }
        )

        titleDetailScreen()

        searchScreen()
    }
}