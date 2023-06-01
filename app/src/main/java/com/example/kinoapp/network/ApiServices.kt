package com.example.kinoapp.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/upcoming?api_key=02da584cad2ae31b564d940582770598&language=en-US&page=1")
    fun getSoonMovies(): Call<SoonDTO>

    @GET("movie/popular?api_key=02da584cad2ae31b564d940582770598&language=en-US&page=1")
    fun getPopularMovies(): Call<PopularDTO>

    @GET("genre/movie/list?api_key=02da584cad2ae31b564d940582770598&language=en-US")
    fun getGenres(): Call<GenresDTO>

    @GET("discover/movie?api_key=02da584cad2ae31b564d940582770598")
    fun getMovieById(
        @Query("with_genres") id: Int
    ): Call<MovieByIdDTO>

    @GET("movie/popular?api_key=02da584cad2ae31b564d940582770598&language=en-US&page=1")
    fun getFavoritesMovies(): Call<FavoritesDTO>
}