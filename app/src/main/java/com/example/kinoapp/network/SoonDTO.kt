package com.example.kinoapp.network

import com.google.gson.annotations.SerializedName

//data class SoonObjectDTO(
//    val upcoming: SoonDTO
//)

data class SoonDTO(
    val results: List<ResultDTO>,
)

data class ResultDTO(
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

