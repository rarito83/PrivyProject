package com.example.privyproject.view

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.privyproject.BuildConfig
import com.example.privyproject.data.DetailMovie
import com.example.privyproject.databinding.ActivityDetailMovieBinding
import com.example.privyproject.di.IMAGE_URL
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailMovieBinding
    private val detailVM: DetailMovieViewModel by viewModel()
    private val api_key = BuildConfig.API_KEY

    companion object {
        const val EXTRA_MOVIE = "extra movie"
        const val EXTRA_SELECT_MOVIE = "extra select movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        detailBinding.imgBackMov.setOnClickListener { finish() }

        setupStatusBar()

        intent.extras.also {
            if (it != null) {
                val id = it.getString(EXTRA_MOVIE)
                val selectItem = it.getString(EXTRA_SELECT_MOVIE)
                Log.d("Detail Movie", "value intent id : $id")

                if (id != null && selectItem != null) {
                    detailVM.apply {
                        getDetailMovie(id.toInt(), api_key)
                    }
                }
            }

            detailVM.detailMovieLiveData.observe(
                this
            ) {
                it?.data?.let { movie ->
                    setDetailMovie(movie)
                }
            }
        }
    }

    private fun setupStatusBar() {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    private fun setDetailMovie(movie: DetailMovie) {
        val genre = movie.genres.map { it.name }.toString().replace("[", "")
            .replace("]", "")

        detailBinding.tvTitleMov.text = movie.originalTitle
        detailBinding.tvDescriptionMov.text = movie.overview
        detailBinding.tvReleaseMov.text = movie.releaseDate
        detailBinding.tvGenresMov.text = genre
        detailBinding.tvRatingMov.text = movie.voteAverage.toString()

        Glide.with(this)
            .load(IMAGE_URL + movie.posterPath)
            .into(detailBinding.imgPosterMov)

        Glide.with(this)
            .load(IMAGE_URL + movie.backdropPath)
            .into(detailBinding.imgBackgroundMov)
    }
}