package com.example.kinoapp.network

import com.google.gson.annotations.SerializedName

data class MovieByIdDTO(
    val results: List<MovieIdDTO>,
)

data class MovieIdDTO(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("genre_ids")
    val genre: List<Int>
)
