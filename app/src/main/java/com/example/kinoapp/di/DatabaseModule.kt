package com.example.kinoapp.di

import com.example.kinoapp.database.AppDatabase
import com.example.kinoapp.database.FavoritesDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single { AppDatabase.getInstance(androidContext()) }
    single { provideFavoritesDao(get()) }

}

fun provideFavoritesDao(appDatabase: AppDatabase): FavoritesDao {
    return appDatabase.favoritesDao()
}