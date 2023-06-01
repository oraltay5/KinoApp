package com.example.kinoapp.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinoapp.genres.model.Genres
import com.example.kinoapp.movies.tabs.soon.model.Soon
import com.example.kinoapp.network.ApiServices
import com.example.kinoapp.network.GenresDTO
import com.example.kinoapp.network.SoonDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresViewModel(
    private val apiServices: ApiServices
): ViewModel() {

    private val _basketDetailData = MutableLiveData<List<Genres>>()
    val basketDetailData: LiveData<List<Genres>>
        get() = _basketDetailData

    private val genresObject = mutableListOf<Genres>()

    fun onViewCreated(){
        apiServices.getGenres().enqueue(object : Callback<GenresDTO> {
            override fun onResponse(call: Call<GenresDTO>, response: Response<GenresDTO>) {
                val responseSuccess = response.body()
                genresObject.clear()

                responseSuccess?.let {genresDTO ->
                    genresDTO.genres.forEach{
                        genresObject.add(
                            Genres(
                                id = it.id,
                                name = it.name
                            )
                        )
                    }
                    _basketDetailData.value = genresObject
                }
            }

            override fun onFailure(call: Call<GenresDTO>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "ERROR", Toast.LENGTH_SHORT).show()
            }
        })
    }
}