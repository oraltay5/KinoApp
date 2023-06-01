package com.example.kinoapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kinoapp.database.entities.FavoritesEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewFavoriteMovie(favorites: FavoritesEntity)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): List<FavoritesEntity>

    @Query("DELETE FROM favorites WHERE id = :id")
    fun deleteFavoriteById(id: Int)

    //    @Query("SELECT * FROM favorites WHERE id = :id")
    //    fun getGroupById(id: Long): FavoritesEntity
}