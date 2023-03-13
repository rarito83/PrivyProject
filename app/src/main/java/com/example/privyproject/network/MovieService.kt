package com.example.privyproject.network

import com.example.privyproject.data.DetailMovie
import com.example.privyproject.data.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
        @Query("with_genres") with_genres: Int
    ): Response<MovieResponse>

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Response<DetailMovie>
}