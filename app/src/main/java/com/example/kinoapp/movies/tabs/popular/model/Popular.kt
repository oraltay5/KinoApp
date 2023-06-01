package com.example.kinoapp.movies.tabs.popular.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Popular(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("genre_ids")
    val genre: List<Int>,
    @SerializedName("original_language")
    val originalLanguage: String,
    val overview: String,
//    var isClicked: Boolean = false
): Parcelable
