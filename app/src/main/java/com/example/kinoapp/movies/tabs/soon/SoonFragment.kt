package com.example.kinoapp.movies.tabs.soon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinoapp.R
import com.example.kinoapp.movies.tabs.soon.detail.SoonDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class SoonFragment: Fragment(R.layout.fragment_soon) {

    private val viewModel by viewModel<SoonViewModel>()

    lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        observeViewModel()
        viewModel.onViewCreated()
    }

    private fun setupView(view: View){
        recyclerView = view.findViewById<RecyclerView>(R.id.soonRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeViewModel(){
        viewModel.basketDetailData.observe(viewLifecycleOwner){ soonObject ->
            recyclerView.adapter = SoonRecyclerAdapter(
                item = soonObject,
                onItemClickListener = { soon ->
                    val intent = Intent(activity, SoonDetailActivity::class.java)
                    intent.putExtra("ARG_SOON", soon)
                    startActivity(intent)
                }
            )
        }
    }
}