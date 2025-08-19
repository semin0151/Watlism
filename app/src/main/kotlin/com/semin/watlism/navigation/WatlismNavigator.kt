package com.semin.watlism.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
class WatlismNavigator(
    val navigatorController: NavHostController
)

@Composable
fun rememberWatlismNavigator(): WatlismNavigator {
    val navController = rememberNavController()
    return remember { WatlismNavigator(navController) }
}