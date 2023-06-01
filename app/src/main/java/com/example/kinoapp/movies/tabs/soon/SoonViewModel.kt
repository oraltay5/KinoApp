package com.example.kinoapp.movies.tabs.soon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinoapp.movies.tabs.soon.model.Soon
import com.example.kinoapp.network.ApiServices
import com.example.kinoapp.network.SoonDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SoonViewModel(
    private val apiServices: ApiServices
): ViewModel() {

    private val _basketDetailData = MutableLiveData<List<Soon>>()
    val basketDetailData: LiveData<List<Soon>>
        get() = _basketDetailData

    private val soonObject = mutableListOf<Soon>()

    fun onViewCreated(){
        apiServices.getSoonMovies().enqueue(object : Callback<SoonDTO> {
            override fun onResponse(call: Call<SoonDTO>, response: Response<SoonDTO>) {
                val responseSuccess = response.body()

                responseSuccess?.let {soonDTO ->
                    soonDTO.results.forEach{
                        soonObject.add(
                            Soon(
                                title = it.title,
                                voteAverage = it.voteAverage,
                                posterPath = "https://image.tmdb.org/t/p/w500/${it.posterPath}",
                                genre = it.genre,
                                originalLanguage = it.originalLanguage,
                                overview = it.overview
                            )
                        )
                    }
                    _basketDetailData.value = soonObject
                }
            }

            override fun onFailure(call: Call<SoonDTO>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }
}