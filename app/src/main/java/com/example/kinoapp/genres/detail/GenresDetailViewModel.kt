package com.example.kinoapp.genres.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinoapp.genres.detail.model.MovieById
import com.example.kinoapp.network.ApiServices
import com.example.kinoapp.network.MovieByIdDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresDetailViewModel(
    private val apiServices: ApiServices
): ViewModel() {

    private val _movieByIdData = MutableLiveData<List<MovieById>>()
    val movieByIdData: LiveData<List<MovieById>>
        get() = _movieByIdData

    fun getMovieById(id: Int?) {
        id?.let {
            apiServices.getMovieById(id).enqueue(object : Callback<MovieByIdDTO> {
                override fun onResponse(call: Call<MovieByIdDTO>, response: Response<MovieByIdDTO>) {
                    val responseSuccess = response.body()

                    responseSuccess?.let {movieByIdDTO ->
                        val movieByIdObject = movieByIdDTO.results.map{
                            MovieById(
                                id = it.id,
                                title = it.title,
                                voteAverage = it.voteAverage,
                                posterPath = "https://image.tmdb.org/t/p/w500/${it.posterPath}",
                                genre = it.genre,
                                originalLanguage = it.originalLanguage,
                                overview = it.overview
                            )
                        }
                        _movieByIdData.value = movieByIdObject
                    }
                }

                override fun onFailure(call: Call<MovieByIdDTO>, t: Throwable) {
                    // Handle error
                }
            })
        }
    }
}
