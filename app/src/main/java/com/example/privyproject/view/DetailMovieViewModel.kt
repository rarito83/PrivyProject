package com.example.privyproject.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.privyproject.data.DetailMovie
import com.example.privyproject.data.MovieResponse
import com.example.privyproject.domain.MovieInteractor
import com.example.privyproject.extension.DataHolder
import com.example.privyproject.extension.launchViewModelScope
import com.example.privyproject.extension.loadingData
import com.example.privyproject.extension.responseData

class DetailMovieViewModel(
    private val movieInteractor: MovieInteractor
) : BaseViewModel() {

    private val _detailMovieLiveData = MutableLiveData<DataHolder<DetailMovie?>>()
    val detailMovieLiveData: LiveData<DataHolder<DetailMovie?>>
        get() = _detailMovieLiveData

    fun getDetailMovie(
        id: Int,
        api_key: String
    ) = launchViewModelScope {
        _detailMovieLiveData
            .loadingData()
            .responseData(
                movieInteractor.getDetailMovie(
                    id,
                    api_key
                )
            )
    }

    companion object {
        const val MOVIE_DETAIL = "movie detail"
    }
}