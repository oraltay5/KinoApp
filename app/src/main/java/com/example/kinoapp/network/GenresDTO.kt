package com.example.kinoapp.network

import com.google.gson.annotations.SerializedName

data class GenresDTO(
    val genres: List<GenreDTO>,
)

data class GenreDTO(
    val id: Int,
    val name: String
)
