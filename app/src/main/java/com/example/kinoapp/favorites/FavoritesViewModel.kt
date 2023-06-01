package com.example.kinoapp.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinoapp.database.AppDatabase
import com.example.kinoapp.database.entities.FavoritesEntity
import com.example.kinoapp.network.ApiServices
import com.example.kinoapp.network.FavoritesDTO
import com.example.kinoapp.network.PopularDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesViewModel(
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
            withContext(Dispatchers.IO) {
                appDatabase.favoritesDao().deleteFavoriteById(id)
            }
            // Обновления basketDetailData после удаления movie
            onViewCreated()
        }
    }
}