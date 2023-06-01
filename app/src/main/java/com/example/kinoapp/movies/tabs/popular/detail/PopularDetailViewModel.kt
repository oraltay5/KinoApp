package com.example.kinoapp.movies.tabs.popular.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.database.AppDatabase
import com.example.kinoapp.database.entities.FavoritesEntity
import com.example.kinoapp.movies.tabs.popular.model.Popular
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularDetailViewModel(
    private val appDatabase: AppDatabase
): ViewModel() {

    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit>
        get() = _success

    private val _isfavorite = MutableLiveData<Boolean>()
    val isfavorite: LiveData<Boolean>
        get() = _isfavorite

    fun saveFavoriteInfo(popular: Popular?){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                appDatabase.favoritesDao().addNewFavoriteMovie(
                    FavoritesEntity(
                        id = popular!!.id,
                        title = popular.title,
                        posterPath = popular.posterPath,
                        voteAverage = popular.voteAverage,
//                        genre = popular.genre,
                        originalLanguage = popular.originalLanguage,
                        overview = popular.overview
                    )
                )
            }
            _success.value = Unit
        }
    }

    fun checkForFavorite (id: Int){
        viewModelScope.launch {
            val favorites = withContext(Dispatchers.IO){
                appDatabase.favoritesDao().getAllFavorites()
            }
            val isFavorite = favorites.find {
                it.id == id
            }
            _isfavorite.value = isFavorite != null
        }
    }
}