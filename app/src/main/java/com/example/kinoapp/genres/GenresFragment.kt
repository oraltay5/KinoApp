package com.example.kinoapp.genres

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.genres.detail.GenresDetailFragment
import com.example.kinoapp.movies.tabs.soon.SoonFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenresFragment: Fragment(R.layout.fragment_genres) {

    private val viewModel by viewModel<GenresViewModel>()

    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        observeViewModel()
        viewModel.onViewCreated()
    }

    private fun setupView(view: View){
        recyclerView = view.findViewById<RecyclerView>(R.id.genresRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun observeViewModel(){
        viewModel.basketDetailData.observe(viewLifecycleOwner){ genresObject ->
            recyclerView.adapter = GenresRecyclerAdapter(
                item = genresObject,
                onItemClickListener = { genres ->
                    val bundle = Bundle()
                    bundle.putParcelable("ARG_GENRE_ID", genres)

                    val detailFragment = GenresDetailFragment()
                    detailFragment.arguments = bundle

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit()
                }
            )
        }
    }
}