package com.example.kinoapp.di

import com.example.kinoapp.network.createApplicationService
import org.koin.dsl.module

val networkModule = module{
    single { createApplicationService(" https://api.themoviedb.org/3/") }
}