package com.example.kinoapp.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorites")
class FavoritesEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0L,
    @PrimaryKey
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
//    @SerializedName("genre_ids")
//    val genre: List<Int>,
    @SerializedName("original_language")
    val originalLanguage: String,
    val overview: String,
//    var isClicked: Boolean = false
): Parcelable
