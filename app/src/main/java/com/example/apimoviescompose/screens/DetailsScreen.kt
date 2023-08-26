@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.apimoviescompose.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.apimoviescompose.R
import com.example.apimoviescompose.screens.view_model.MoviesViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(itemId: Int, viewModel: MoviesViewModel, navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val movie = viewModel.listMovies.find { it.id == itemId }

    val orientation = LocalConfiguration.current.orientation

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Scaffold(
            topBar = { TopBar(navController = navController) },
        ) {
            movie?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                        ) {
                            val painterBackdrop = rememberAsyncImagePainter(
                                model = it.backdrop_path,
                            )

                            Image(
                                painter = painterBackdrop,
                                contentDescription = "portada",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )

                            if (it.adult) {
                                Icon(
                                    painter = painterResource(id = R.drawable.adult),
                                    contentDescription = "adult",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .align(Alignment.TopEnd)
                                        .padding(top = 10.dp, end = 2.dp),
                                    tint = Color.Red
                                )
                            }

                        }
                        Text(
                            text = it.title,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            modifier = Modifier
                                .padding(top = 12.dp)
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Date"
                            )
                            Text(text = it.release_date)
                        }

                        Divider(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp))

                        Row(
                            modifier = Modifier
                                .padding(top = 18.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "star",
                                tint = Color.Yellow,
                            )
                            Text(text = it.vote_average.toString())
                        }

                        LazyRow(
                            modifier = Modifier
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            items(it.genre_ids) { genreId ->
                                Card(
                                    shape = RoundedCornerShape(22.dp)
                                ) {
                                    Text(
                                        text = viewModel.getGenre(genreId),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(8.dp),
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                        Text(
                            text = "Overview",
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 16.dp)
                        )
                        Divider(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp))

                        Text(
                            text = it.overview,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    } else {
        Scaffold(
            topBar = { TopBar(navController = navController) },
        ) {
            movie?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp),

                    ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                    ) {
                        val painterBackdrop = rememberAsyncImagePainter(
                            model = it.backdrop_path,
                        )

                        Image(
                            painter = painterBackdrop,
                            contentDescription = "portada",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )

                        if (it.adult) {
                            Icon(
                                painter = painterResource(id = R.drawable.adult),
                                contentDescription = "adult",
                                modifier = Modifier
                                    .size(35.dp)
                                    .align(Alignment.TopEnd)
                                    .padding(top = 10.dp, end = 2.dp),
                                tint = Color.Red
                            )
                        }

                    }
                    Text(
                        text = it.title,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        modifier = Modifier
                            .align(
                                Alignment.CenterHorizontally
                            )
                            .padding(top = 12.dp)
                    )
                    Row(
                        modifier = Modifier
                            .align(
                                Alignment.CenterHorizontally
                            )
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Date"
                        )
                        Text(text = it.release_date)
                    }

                    Divider(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp))

                    Row(
                        modifier = Modifier
                            .align(
                                Alignment.CenterHorizontally
                            )
                            .padding(top = 18.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "star",
                            tint = Color.Yellow,
                        )
                        Text(text = it.vote_average.toString())
                    }

                    LazyRow(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(it.genre_ids) { genreId ->
                            Card(
                                shape = RoundedCornerShape(22.dp)
                            ) {
                                Text(
                                    text = viewModel.getGenre(genreId),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                    Text(
                        text = "Overview",
                        textAlign = TextAlign.Center,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(
                                Alignment.CenterHorizontally
                            )
                            .padding(top = 16.dp)
                    )
                    Divider(modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp))
                    LazyColumn(modifier = Modifier.padding(12.dp)) {
                        item {
                            Text(
                                text = it.overview
                            )
                        }
                    }


                }
            }
        }
    }





    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.getMovieAPI()
        }
    }
}

@Composable
fun TopBar(navController: NavController) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Movies",
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "goBack")
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

