package com.example.kinoapp.genres.detail.movieById

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.database.AppDatabase
import com.example.kinoapp.database.entities.FavoritesEntity
import com.example.kinoapp.genres.detail.model.MovieById
import com.example.kinoapp.movies.tabs.popular.model.Popular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieByIdViewModel(
    private val appDatabase: AppDatabase
): ViewModel() {

    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit>
        get() = _success

    fun saveFavoriteInfo(movieById: MovieById?){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                appDatabase.favoritesDao().addNewFavoriteMovie(
                    FavoritesEntity(
                        id = movieById!!.id,
                        title = movieById.title,
                        posterPath = movieById.posterPath,
                        voteAverage = movieById.voteAverage ,
//                        genre = popular.genre,
                        originalLanguage = movieById.originalLanguage,
                        overview = movieById.overview
                    )
                )
            }
            _success.value = Unit
        }
    }
}