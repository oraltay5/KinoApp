package com.example.kinoapp.favorites

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.favorites.detail.FavoritesDetailActivity
import com.example.kinoapp.movies.tabs.popular.PopularRecyclerAdapter
import com.example.kinoapp.movies.tabs.popular.PopularViewModel
import com.example.kinoapp.movies.tabs.popular.detail.PopularDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment: Fragment(R.layout.fragment_favorites) {

    private val viewModel by viewModel<FavoritesViewModel>()

    lateinit var recyclerView: RecyclerView
    lateinit var favoriteIconView: AppCompatImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewCreated()
    }

    private fun setupView(view: View){
        recyclerView = view.findViewById<RecyclerView>(R.id.favoritesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeViewModel() = with(viewModel){
        basketDetailData.observe(viewLifecycleOwner) { favoritesList ->
            recyclerView.adapter = FavoritesRecyclerAdapter(
                item = favoritesList,
                onItemClickListener = { favorite ->
                    val intent = Intent(activity, FavoritesDetailActivity::class.java)
                    intent.putExtra("ARG_FAVORITES", favorite)
                    startActivity(intent)
                },
                onFavoriteClickListener = {
                    deleteChat(it.id)
                }
            )
        }
    }
}