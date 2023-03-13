package com.example.privyproject.data

import com.example.privyproject.domain.MovieRepository
import com.example.privyproject.network.MovieService
import retrofit2.Response

class MovieRepositoryImpl constructor(private val movieService: MovieService) :
    MovieRepository {

    override suspend fun getMovies(apiKey: String, page: Int, with_genres: Int): Response<MovieResponse> {
        return movieService.getDiscoverMovies(apiKey, page, with_genres)
    }

    override suspend fun getDetailMovie(id: Int, apiKey: String): Response<DetailMovie> {
        return movieService.getDetailMovie(id, apiKey)
    }
}