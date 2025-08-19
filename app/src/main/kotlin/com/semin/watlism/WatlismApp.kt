package com.semin.watlism

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.semin.watlism.navigation.WatlismNavHost
import com.semin.watlism.navigation.rememberWatlismNavigator

@Composable
fun WatlismApp(
    modifier: Modifier = Modifier
) {
    val navigator = rememberWatlismNavigator()

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) { padding ->
        WatlismNavHost(navigator, modifier = Modifier.padding(padding))
    }
}