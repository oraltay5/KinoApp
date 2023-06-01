package com.example.kinoapp.favorites.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.kinoapp.R
import com.example.kinoapp.database.entities.FavoritesEntity
import com.example.kinoapp.favorites.FavoritesRecyclerAdapter
import com.example.kinoapp.favorites.model.Favorites
import com.example.kinoapp.movies.tabs.popular.detail.PopularDetailViewModel
import com.example.kinoapp.movies.tabs.soon.model.Soon
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesDetailActivity: AppCompatActivity() {
    var favorites: FavoritesEntity? = null
    private val viewModel by viewModel<FavoritesDetailViewModel>()

    lateinit var favoriteIconView: AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_detail)

        favorites = intent.getParcelableExtra<FavoritesEntity>("ARG_FAVORITES") as FavoritesEntity
        setupView()
//        observeViewModel()

        val backIcon = findViewById<AppCompatImageView>(R.id.backView)
        backIcon.setOnClickListener {
            finish()
        }
    }

    private fun setupView() {
        val titleView = findViewById<TextView>(R.id.titleView)
        val genreView = findViewById<TextView>(R.id.genreView)
        val posterView = findViewById<AppCompatImageView>(R.id.posterView)
        val voteAverageView = findViewById<TextView>(R.id.voteAverageView)
        val overview = findViewById<TextView>(R.id.overview)
        val favoriteClickView = findViewById<AppCompatImageView>(R.id.favoriteClickView)

        titleView.text = "${favorites?.title}"
//        genreView.text = "${favorites?.genre}"
        voteAverageView.text = "${favorites?.voteAverage}"
        overview.text = "${favorites?.overview}"
        Glide
            .with(posterView)
            .load("${favorites?.posterPath}")
            .centerCrop()
            .placeholder(R.drawable.enot)
            .into(posterView)
    }

//    private fun observeViewModel() {
//        viewModel.basketDetailData.observe(this, { favoritesList ->
//            favoriteClickView.adapter = FavoritesRecyclerAdapter(
//                item = favoritesList,
//                onItemClickListener = { favorite ->
//                    deleteChat(favorite.id)
//                }
//            )
//        })
//    }

}