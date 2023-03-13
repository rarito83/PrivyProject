package com.example.privyproject.domain

import com.example.privyproject.data.DetailMovie
import com.example.privyproject.data.MovieResponse
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovies(
        api_key: String,
        page: Int,
        with_genres: Int
    ): Response<MovieResponse>

    suspend fun getDetailMovie(
        id: Int,
        api_key: String
    ): Response<DetailMovie>
}