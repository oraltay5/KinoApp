package com.example.kinoapp.di

import com.example.kinoapp.favorites.FavoritesViewModel
import com.example.kinoapp.genres.GenresViewModel
import com.example.kinoapp.genres.detail.GenresDetailViewModel
import com.example.kinoapp.genres.detail.movieById.MovieByIdViewModel
import com.example.kinoapp.movies.tabs.popular.PopularViewModel
import com.example.kinoapp.movies.tabs.popular.detail.PopularDetailViewModel
import com.example.kinoapp.movies.tabs.soon.SoonViewModel
import com.example.kinoapp.movies.tabs.soon.detail.SoonDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SoonViewModel(get()) }
    viewModel { SoonDetailViewModel(get())}
    viewModel { PopularViewModel(get()) }
    viewModel { PopularDetailViewModel(get()) }
    viewModel { GenresViewModel(get()) }
    viewModel { GenresDetailViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { MovieByIdViewModel(get()) }
}