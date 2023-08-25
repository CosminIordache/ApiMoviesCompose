package com.example.apimoviescompose.data

import com.example.apimoviescompose.model.ResultsMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("3/movie/popular")
    suspend fun getMovies(@Query("api_key") key:String, @Query("lenguage") leng:String): Response<ResultsMovies>
}