package com.example.apimoviescompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.apimoviescompose.screens.DetailsScreen
import com.example.apimoviescompose.screens.MoviesScreen
import com.example.apimoviescompose.screens.view_model.MoviesViewModel
import com.example.apimoviescompose.util.Util

@Composable
fun NavGraph(isDarkMode: MutableState<Boolean>){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Util.MOVIE_SCREEN){

        composable(Util.MOVIE_SCREEN){
            val viewModel: MoviesViewModel = viewModel()
            MoviesScreen(viewModel = viewModel, navController = navController, isDarkMode)
        }

        composable(
            route = Util.DETAILS_SCREEN+ "{itemId}",
            arguments = listOf(navArgument("itemId"){type = NavType.IntType})
        ){moviePosition ->
            val viewModel: MoviesViewModel = viewModel()
            val movieId = moviePosition.arguments?.getInt("itemId")
            movieId?.let {id ->
                DetailsScreen(itemId = id, viewModel = viewModel, navController = navController)
            }
        }
    }
}