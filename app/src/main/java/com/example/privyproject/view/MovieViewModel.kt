package com.example.privyproject.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.privyproject.data.MovieResponse
import com.example.privyproject.domain.MovieInteractor
import com.example.privyproject.extension.DataHolder
import com.example.privyproject.extension.launchViewModelScope
import com.example.privyproject.extension.loadingData
import com.example.privyproject.extension.responseData

class MovieViewModel (
    private val movieInteractor: MovieInteractor
) : BaseViewModel() {

    private val _moviesLiveData = MutableLiveData<DataHolder<MovieResponse?>>()
    val moviesLiveData: LiveData<DataHolder<MovieResponse?>>
        get() = _moviesLiveData

    fun getMovies(
        api_key: String,
        page: Int,
        with_genres: Int
    ) = launchViewModelScope {
        _moviesLiveData
            .loadingData()
            .responseData(
                movieInteractor.getMovies(
                    api_key,
                    page,
                    with_genres
                )
            )
    }

    companion object {
        private const val TAG = "MovieViewModel"
    }
}