package com.example.kinoapp.genres.detail.movieById

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.kinoapp.R
import com.example.kinoapp.genres.detail.model.MovieById
import com.example.kinoapp.movies.tabs.popular.detail.PopularDetailViewModel
import com.example.kinoapp.movies.tabs.popular.model.Popular
import com.example.kinoapp.movies.tabs.soon.model.Soon
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieByIdActivity: AppCompatActivity() {
    var movieById: MovieById? = null
    private val viewModel by viewModel<MovieByIdViewModel>()
    private var isClicked = false
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_by_id)

        preferences = getSharedPreferences("MOVIE_PREFS", MODE_PRIVATE)
        isClicked = preferences.getBoolean("IS_CLICKED", false)

        movieById = intent.getParcelableExtra<MovieById>("ARG_FROM_GENRE") as MovieById

        setupView()
        observeViewModel()

        val backIcon = findViewById<AppCompatImageView>(R.id.backView)
        backIcon.setOnClickListener {
            finish()
        }

    }

    override fun onPause() {
        super.onPause()
        preferences.edit().putBoolean("IS_CLICKED", isClicked).apply()
    }

    override fun onResume() {
        super.onResume()
        isClicked = preferences.getBoolean("IS_CLICKED", false)
        setupView()
    }

    private fun setupView() {
        val titleView = findViewById<TextView>(R.id.titleView)
        val genreView = findViewById<TextView>(R.id.genreView)
        val posterView = findViewById<AppCompatImageView>(R.id.posterView)
        val voteAverageView = findViewById<TextView>(R.id.voteAverageView)
        val overview = findViewById<TextView>(R.id.overview)
        val favoriteClickView = findViewById<AppCompatImageView>(R.id.favoriteClickView)

        titleView.text = "${movieById?.title}"
        genreView.text = "${movieById?.genre}"
        voteAverageView.text = "${movieById?.voteAverage}"
        overview.text = "${movieById?.overview}"
        Glide
            .with(posterView)
            .load("${movieById?.posterPath}")
            .centerCrop()
            .placeholder(R.drawable.enot)
            .into(posterView)


        if (isClicked) {
            favoriteClickView.setImageResource(R.drawable.ic_favorite)
        } else {
            favoriteClickView.setImageResource(R.drawable.ic_favorite_click)
        }
        favoriteClickView.setOnClickListener {
            if (isClicked) {
                isClicked = false
                favoriteClickView.setImageResource(R.drawable.ic_favorite_click)
            } else {
                isClicked = true
                favoriteClickView.setImageResource(R.drawable.ic_favorite)
            }
            viewModel.saveFavoriteInfo(movieById)
        }
    }

    private fun observeViewModel() = with(viewModel) {
        success.observe(this@MovieByIdActivity){
        }
    }
}