package com.example.privyproject.di

import com.example.privyproject.data.MovieRepositoryImpl
import com.example.privyproject.domain.MovieInteractor
import com.example.privyproject.domain.MovieRepository
import com.example.privyproject.network.MovieService
import com.example.privyproject.network.createNetworkClient
import com.example.privyproject.view.DetailMovieViewModel
import com.example.privyproject.view.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModules = module {
    single {
        provideMoviesRepository(get())
    }
}

val interactorModules = module {
    factory {
        MovieInteractor(get())
    }
}

val networkModules = module {
    single {
        createNetworkClient(BASE_URL)
    }
    single {
        (get() as Retrofit).create(MovieService::class.java)
    }
}

val viewModelModules = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}

private fun provideMoviesRepository(movieService: MovieService): MovieRepository =
    MovieRepositoryImpl(movieService = movieService)

const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
private const val BASE_URL = "https://api.themoviedb.org/3/"
