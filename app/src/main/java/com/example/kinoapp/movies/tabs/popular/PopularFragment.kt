package com.example.kinoapp.movies.tabs.popular

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.movies.tabs.popular.detail.PopularDetailActivity
import com.example.kinoapp.movies.tabs.soon.SoonRecyclerAdapter
import com.example.kinoapp.movies.tabs.soon.SoonViewModel
import com.example.kinoapp.movies.tabs.soon.detail.SoonDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class PopularFragment: Fragment(R.layout.fragment_popular) {

    private val viewModel by viewModel<PopularViewModel>()

    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        observeViewModel()
        viewModel.onViewCreated()
    }

    private fun setupView(view: View){
        recyclerView = view.findViewById<RecyclerView>(R.id.popularRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeViewModel(){
        viewModel.basketDetailData.observe(viewLifecycleOwner){ popularObject ->
            recyclerView.adapter = PopularRecyclerAdapter(
                item = popularObject,
                onItemClickListener = { popular ->
                    val intent = Intent(activity, PopularDetailActivity::class.java)
                    intent.putExtra("ARG_POP", popular)
                    startActivity(intent)
                }
            )
        }
    }
}



