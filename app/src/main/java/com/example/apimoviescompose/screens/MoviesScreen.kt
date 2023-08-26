@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apimoviescompose.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apimoviescompose.design.CardMovie
import com.example.apimoviescompose.screens.view_model.MoviesViewModel
import com.example.apimoviescompose.ui.theme.ApiMoviesComposeTheme
import com.example.apimoviescompose.util.Util
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MoviesScreen(viewModel: MoviesViewModel, navController: NavController, isDarkMode: MutableState<Boolean> ) {

    val coroutineScope = rememberCoroutineScope()
    val moviesState = viewModel.listMovies
    val isSheetOpen = remember { mutableStateOf(false) }

    val orientation = LocalConfiguration.current.orientation

    val gridSize = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        4
    } else {
        2
    }


    Scaffold(
        topBar = { TopAppBar(isSheetOpen) }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(gridSize),
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
        if (isSheetOpen.value) {
            BottomSheet(isSheetOpen, isDarkMode)
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
fun TopAppBar(isSheetOpen: MutableState<Boolean>) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Movies",
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = {
                isSheetOpen.value = true
            }) {
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

@Composable
fun BottomSheet(isSheetOpen: MutableState<Boolean>, isDarkMode: MutableState<Boolean>) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { isSheetOpen.value = false },
        sheetState = sheetState
    ) {
        Row(
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Dark Mode", fontSize = 18.sp)
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                modifier = Modifier,
                checked = isDarkMode.value,
                onCheckedChange = {
                    isDarkMode.value = !isDarkMode.value
                }
            )
        }
    }
}



