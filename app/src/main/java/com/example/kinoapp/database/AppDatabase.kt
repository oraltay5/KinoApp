package com.example.kinoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kinoapp.database.entities.FavoritesEntity

@Database(
    version = 1,
    entities = [
        FavoritesEntity::class,
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favoritesDao() : FavoritesDao

    companion object {
        private val LOCK = Any()

        @Volatile
        private var sInstance: AppDatabase? = null
        private const val DATABASE_NAME = "kinoapp_db"

        fun getInstance(context: Context): AppDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(
                            context, AppDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }
            return sInstance
        }
    }
}