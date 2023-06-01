package com.example.kinoapp.movies.tabs.soon.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.database.AppDatabase
import com.example.kinoapp.database.entities.FavoritesEntity
import com.example.kinoapp.movies.tabs.popular.model.Popular
import com.example.kinoapp.movies.tabs.soon.model.Soon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SoonDetailViewModel(
    private val appDatabase: AppDatabase
): ViewModel() {

    private val _success = MutableLiveData<Unit>()
    val success: LiveData<Unit>
        get() = _success

    fun saveFavoriteInfo(soon: Soon?){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                appDatabase.favoritesDao().addNewFavoriteMovie(
                    FavoritesEntity(
                        id = 0,
                        title = soon!!.title,
                        posterPath = soon.posterPath,
                        voteAverage = soon.voteAverage ,
//                        genre = popular.genre,
                        originalLanguage = soon.originalLanguage,
                        overview = soon.overview
                    )
                )
            }
            _success.value = Unit
        }
    }
}