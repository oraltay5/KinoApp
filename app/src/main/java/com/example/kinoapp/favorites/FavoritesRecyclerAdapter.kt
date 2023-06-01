package com.example.kinoapp.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.kinoapp.R
import com.example.kinoapp.database.entities.FavoritesEntity
import com.example.kinoapp.movies.tabs.popular.model.Popular

class FavoritesRecyclerAdapter(
    private val item: List<FavoritesEntity>,
    private val onItemClickListener: (FavoritesEntity) -> Unit,
    private val onFavoriteClickListener: (FavoritesEntity) -> Unit
) : RecyclerView.Adapter<FavoritesRecyclerAdapter.ViewHolder>() {

    private val favoritesItems = mutableListOf<FavoritesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_favorites, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        lateinit var titleView: TextView
        lateinit var posterView: ImageView
        lateinit var voteAverageView: TextView
        lateinit var genreView: TextView
        lateinit var languageView: TextView
        lateinit var itemViewGroup: RelativeLayout
        lateinit var favoriteIconView: AppCompatImageView


        fun bind(item: FavoritesEntity){
            titleView = itemView.findViewById(R.id.titleView)
            posterView = itemView.findViewById(R.id.posterView)
            voteAverageView = itemView.findViewById(R.id.ratingView)
            genreView = itemView.findViewById(R.id.genreView)
            languageView = itemView.findViewById(R.id.languageView)
            itemViewGroup = itemView.findViewById(R.id.itemFavViewGroup)
            favoriteIconView = itemView.findViewById(R.id.favoriteIconView)


            titleView.text = item.title
            voteAverageView.text = item.voteAverage.toString()
//            genreView.text = item.genre.toString()
            languageView.text = item.originalLanguage



            val radius = 24
            Glide
                .with(itemView)
                .load(item.posterPath)
                .centerCrop()
                .placeholder(R.drawable.ic_movies)
                .apply(RequestOptions().transforms(RoundedCorners(radius)))
                .into(posterView)

            itemViewGroup.setOnClickListener {
                onItemClickListener(item)
            }

            favoriteIconView.setOnClickListener{
                onFavoriteClickListener(item)
            }
        }
    }
}