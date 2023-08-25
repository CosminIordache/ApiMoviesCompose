package com.example.apimoviescompose.screens.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.apimoviescompose.data.ApiService
import com.example.apimoviescompose.model.Result
import com.example.apimoviescompose.util.Util


class MoviesViewModel: ViewModel() {

    val listMovies = mutableStateListOf<Result>()
    var isLoading = mutableStateOf(true)

    suspend fun getMovieAPI() {
        val call = ApiService.movieApi.getMovies(key = "9fbea8d0a3e8c5d1ab5573cb33b9282c", leng = "en-US")

        if (call.isSuccessful){
            val movies = call.body()
            val result = movies?.results ?: emptyList()
            listMovies.clear()
            val list: List<Result> = result.map {
                Result(
                    adult = it.adult,
                    backdrop_path = Util.BaseUrlImg + it.backdrop_path,
                    genre_ids = it.genre_ids,
                    id = it.id,
                    original_language = it.original_language,
                    original_title = it.original_title,
                    overview = it.overview,
                    popularity = it.popularity,
                    poster_path = Util.BaseUrlImg + it.poster_path,
                    release_date = it.release_date,
                    title = it.title,
                    video = it.video,
                    vote_average = it.vote_average,
                    vote_count = it.vote_count
                )
            }
            listMovies.addAll(list)
            isLoading.value = false
        }else{
         Log.e("test", "error")
        }
    }

    fun getGenre(genderID: Int): String {
        return when (genderID) {
            28 -> "Action"
            12 -> "Adventure"
            16 -> "Animation"
            35 -> "Comedy"
            80 -> "Crime"
            99 -> "Documentary"
            18 -> "Drama"
            10751 -> "Family"
            14 -> "Fantasy"
            36 -> "History"
            27 -> "Horror"
            10402 -> "Music"
            9648 -> "Mystery"
            10749 -> "Romance"
            878 -> "Science Fiction"
            10770 -> "TV Movie"
            53 -> "Thriller"
            10752 -> "War"
            37 -> "Western"
            else -> "Unknown Genre"
        }
    }
}