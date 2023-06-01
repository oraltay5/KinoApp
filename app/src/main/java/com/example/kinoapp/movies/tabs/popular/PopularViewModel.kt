package com.example.kinoapp.movies.tabs.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinoapp.movies.tabs.popular.model.Popular
import com.example.kinoapp.network.ApiServices
import com.example.kinoapp.network.PopularDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewModel(
    private val apiServices: ApiServices
): ViewModel() {

    private val _basketDetailData = MutableLiveData<List<Popular>>()
    val basketDetailData: LiveData<List<Popular>>
        get() = _basketDetailData

    private val popularObject = mutableListOf<Popular>()

    fun onViewCreated(){
        apiServices.getPopularMovies().enqueue(object : Callback<PopularDTO> {
            override fun onResponse(call: Call<PopularDTO>, response: Response<PopularDTO>) {
                val responseSuccess = response.body()

                responseSuccess?.let {popularDTO ->
                    popularDTO.results.forEach{
                        popularObject.add(
                            Popular(
                                id = it.id,
                                title = it.title,
                                voteAverage = it.voteAverage,
                                posterPath = "https://image.tmdb.org/t/p/w500/${it.posterPath}",
                                genre = it.genre,
                                originalLanguage = it.originalLanguage,
                                overview = it.overview,
//                                isClicked = it.isClicked
                            )
                        )
                    }
                    _basketDetailData.value = popularObject
                }
            }

            override fun onFailure(call: Call<PopularDTO>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }
}