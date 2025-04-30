package com.zoroxnekko.palebluecmpassignment

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.PixaImageOverviewPageRoot
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.PixaImageViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.PixaImageOverviewPage
        ) {
            composable<Route.PixaImageOverviewPage> {
                val viewModel = koinViewModel<PixaImageViewModel>()

                PixaImageOverviewPageRoot(
                    viewModel = viewModel
                )
            }


        }
    }
}
