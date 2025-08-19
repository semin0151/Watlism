package com.semin.watlism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.semin.watlism.feature.core.theme.WatlismTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WatlismTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WatlismApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}