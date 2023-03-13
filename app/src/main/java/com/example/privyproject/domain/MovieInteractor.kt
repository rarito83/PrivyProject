package com.example.privyproject.domain

import com.example.privyproject.data.DetailMovie
import com.example.privyproject.data.MovieResponse
import retrofit2.Response

class MovieInteractor(
    private val repository: MovieRepository
) {
    suspend fun getMovies(
        api_key: String,
        page: Int,
        with_genres: Int
    ): Response<MovieResponse> {
        return repository.getMovies(api_key, page, with_genres)
    }

    suspend fun getDetailMovie(
        id: Int,
        api_key: String
    ): Response<DetailMovie> {
        return repository.getDetailMovie(id, api_key)
    }
}