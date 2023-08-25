@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apimoviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.apimoviescompose.navigation.NavGraph
import com.example.apimoviescompose.screens.view_model.MoviesViewModel
import com.example.apimoviescompose.ui.theme.ApiMoviesComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val darkMode = mutableStateOf(false)

        setContent {
            ApiMoviesComposeTheme(darkTheme = darkMode.value) {
                val viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

                val splashScreen = installSplashScreen()
                splashScreen.setKeepOnScreenCondition{ !viewModel.isLoading.value }

                NavGraph(isDarkMode = darkMode)
            }
        }
    }
}

