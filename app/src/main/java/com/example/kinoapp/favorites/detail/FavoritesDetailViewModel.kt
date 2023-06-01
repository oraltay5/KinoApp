package com.example.kinoapp.favorites.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.database.AppDatabase
import com.example.kinoapp.database.entities.FavoritesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesDetailViewModel(
    private val appDatabase: AppDatabase
): ViewModel() {

    private val _basketDetailData = MutableLiveData<List<FavoritesEntity>>()
    val basketDetailData: LiveData<List<FavoritesEntity>>
        get() = _basketDetailData

    fun onViewCreated(){
        viewModelScope.launch {
            val favorites = withContext(Dispatchers.IO){
                appDatabase.favoritesDao().getAllFavorites()
            }
            _basketDetailData.value = favorites
        }
    }

    fun deleteChat(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {   //Функция withContext() - чтобы запрос на удаление выполняется в фоновом потоке
                appDatabase.favoritesDao().deleteFavoriteById(id)
            }
            // Обновления basketDetailData после удаления chat
            onViewCreated()
        }
    }
}