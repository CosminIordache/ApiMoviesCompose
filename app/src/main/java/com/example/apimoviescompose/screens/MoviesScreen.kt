@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apimoviescompose.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apimoviescompose.design.CardMovie
import com.example.apimoviescompose.screens.view_model.MoviesViewModel
import com.example.apimoviescompose.util.Util
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(viewModel: MoviesViewModel, navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val moviesState = viewModel.listMovies


    Scaffold(
        topBar = { TopAppBar() }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top = 70.dp, start = 8.dp, end = 8.dp)
        ) {

            items(items = moviesState, key = { it.id }) { item ->
                CardMovie(
                    image = item.poster_path,
                    vote_avg = item.vote_average,
                    onClick = {
                        navController.navigate(Util.DETAILS_SCREEN + "${item.id}")
                        Log.e("test", item.genre_ids.toString())
                    }
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.getMovieAPI()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Movies",
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}



