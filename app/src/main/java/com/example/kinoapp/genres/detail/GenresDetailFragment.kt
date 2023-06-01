package com.example.kinoapp.genres.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.genres.detail.movieById.MovieByIdActivity
import com.example.kinoapp.genres.model.Genres
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenresDetailFragment : Fragment(R.layout.fragment_genres_detail) {

    private val viewModel by viewModel<GenresDetailViewModel>()
    private lateinit var recyclerView: RecyclerView
    var genreById: Genres? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        observeViewModel()

        arguments?.let {
            genreById = it.getParcelable("ARG_GENRE_ID") ?: throw IllegalArgumentException("ARG_GENRE_ID not found")
        }

        viewModel.getMovieById(genreById?.id)

        val aaa = view.findViewById<FrameLayout>(R.id.fragment_container)
        aaa.setBackgroundResource(R.color.white)
    }

    private fun setupView(view: View) {
        recyclerView = view.findViewById<RecyclerView>(R.id.genresDetailRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeViewModel() {
        viewModel.movieByIdData.observe(viewLifecycleOwner) { movieByIdObject ->
            recyclerView.adapter = GenresDetailRecyclerAdapter(
                item = movieByIdObject,
                onItemClickListener = { movieById ->
                    val intent = Intent(activity, MovieByIdActivity::class.java)
                    intent.putExtra("ARG_FROM_GENRE", movieById)
                    startActivity(intent)
                }
            )
        }
    }
}
