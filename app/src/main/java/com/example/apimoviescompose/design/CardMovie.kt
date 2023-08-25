package com.example.apimoviescompose.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardMovie(image: String, vote_avg: Double, onClick: () -> Unit) {

    Card(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .width(150.dp)
            .height(300.dp)
            .padding(4.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {

            val painter = rememberAsyncImagePainter(
                model = image,
                filterQuality = FilterQuality.High
            )

            Image(
                painter = painter,
                contentDescription = "portada",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Black, Color.Transparent)
                        )
                    )
                    .align(Alignment.TopEnd)
                    .height(80.dp)
                    .padding(8.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "star",
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.padding(start = 5.dp))
                    Text(
                        text = vote_avg.toString(),
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

        }

    }
}