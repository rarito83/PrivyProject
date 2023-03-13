package com.example.privyproject.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.privyproject.BuildConfig
import com.example.privyproject.data.Movie
import com.example.privyproject.databinding.ActivityMainBinding
import com.example.privyproject.view.DetailMovieViewModel.Companion.MOVIE_DETAIL
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickCallback {

    private lateinit var binding: ActivityMainBinding
    private val movieVM: MovieViewModel by viewModel()
    private val api_key = BuildConfig.API_KEY
    private val page = 1
    private val genres = 53

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieVM.getMovies(api_key, page, genres)
        initView()
    }

    private fun initView() {
        with (binding) {
            val adapter = MovieAdapter()

            rvMovie.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                this.adapter = adapter
            }

            movieVM.moviesLiveData.observe(
                this@MainActivity

            ) {
                if (it.data?.results?.isNotEmpty() == true) {
                    it?.data?.results?.let {movies ->
                        adapter.setData(movies as MutableList<Movie>)
                        adapter.setOnItemClickCallback(this@MainActivity)
                    }
                }
            }
        }
    }

    override fun onItemClicked(id: String) {
        Intent(this, DetailMovieActivity::class.java).also { it ->
            it.apply {
                DetailMovieActivity.also {
                    putExtra(it.EXTRA_MOVIE, id)
                    putExtra(it.EXTRA_SELECT_MOVIE, MOVIE_DETAIL)
                    startActivity(this)
                }
            }
        }
    }
}