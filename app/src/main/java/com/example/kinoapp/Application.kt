package com.example.kinoapp

import android.app.Application
import com.example.kinoapp.di.databaseModule
import com.example.kinoapp.di.networkModule
import com.example.kinoapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin{
            androidContext(applicationContext)
            modules(arrayListOf(
                networkModule,
                viewModelModule,
                databaseModule
            ))
        }
    }
}