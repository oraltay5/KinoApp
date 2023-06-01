package com.example.kinoapp.movies.tabs.popular.detail

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.kinoapp.R
import com.example.kinoapp.movies.tabs.popular.model.Popular
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularDetailActivity: AppCompatActivity() {
    var popular: Popular? = null
    private val viewModel by viewModel<PopularDetailViewModel>()
    private var isClicked = false
    private lateinit var preferences: SharedPreferences
    private lateinit var favoriteClickView: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_detail)

        preferences = getSharedPreferences("POPULAR_PREFS", MODE_PRIVATE)
        isClicked = preferences.getBoolean("IS_CLICKED", false)

        popular = intent.getParcelableExtra<Popular>("ARG_POP") as Popular

        setupView()
        viewModel.checkForFavorite(popular?.id?:0)
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
        favoriteClickView = findViewById<AppCompatImageView>(R.id.favoriteClickView)

        titleView.text = "${popular?.title}"
        genreView.text = "${popular?.genre}"
        voteAverageView.text = "${popular?.voteAverage}"
        overview.text = "${popular?.overview}"
        Glide
            .with(posterView)
            .load("${popular?.posterPath}")
            .centerCrop()
            .placeholder(R.drawable.enot)
            .into(posterView)


        favoriteClickView.setOnClickListener {
            if (isClicked) {
                isClicked = false
                favoriteClickView.setImageResource(R.drawable.ic_favorite_click)
            } else {
                isClicked = true
                favoriteClickView.setImageResource(R.drawable.ic_favorite)
            }
//            intent.putExtra("ARG_FROM_POP", popular)
            viewModel.saveFavoriteInfo(popular)
        }
    }

    private fun observeViewModel() = with(viewModel) {
        success.observe(this@PopularDetailActivity){
        }
        isfavorite.observe(this@PopularDetailActivity){
            if (it) {
                favoriteClickView.setImageResource(R.drawable.ic_favorite)
            } else {
                favoriteClickView.setImageResource(R.drawable.ic_favorite_click)
            }
        }
    }
}