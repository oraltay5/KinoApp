package com.example.kinoapp.movies.tabs.soon.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.kinoapp.R
import com.example.kinoapp.movies.tabs.popular.detail.PopularDetailViewModel
import com.example.kinoapp.movies.tabs.popular.model.Popular
import com.example.kinoapp.movies.tabs.soon.model.Soon
import org.koin.androidx.viewmodel.ext.android.viewModel

class SoonDetailActivity: AppCompatActivity() {
    var soon: Soon? = null
    private val viewModel by viewModel<SoonDetailViewModel>()
    private var isClicked = false
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soon_detail)

        preferences = getSharedPreferences("SOON_PREFS", MODE_PRIVATE)
        isClicked = preferences.getBoolean("IS_CLICKED", false)

        soon = intent.getParcelableExtra<Soon>("ARG_SOON") as Soon

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

        titleView.text = "${soon?.title}"
        genreView.text = "${soon?.genre}"
        voteAverageView.text = "${soon?.voteAverage}"
        overview.text = "${soon?.overview}"
        Glide
            .with(posterView)
            .load("${soon?.posterPath}")
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

            viewModel.saveFavoriteInfo(soon)
        }
    }

    private fun observeViewModel() = with(viewModel) {
        success.observe(this@SoonDetailActivity){
        }
    }
}