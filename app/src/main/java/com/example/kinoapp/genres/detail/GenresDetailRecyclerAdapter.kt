package com.example.kinoapp.genres.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.kinoapp.R
import com.example.kinoapp.genres.detail.model.MovieById

class GenresDetailRecyclerAdapter(
    private val item: List<MovieById>,
    private val onItemClickListener: (MovieById) -> Unit,
) : RecyclerView.Adapter<GenresDetailRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie_by_id, parent, false)
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


        fun bind(item: MovieById){
            titleView = itemView.findViewById(R.id.titleView)
            posterView = itemView.findViewById(R.id.posterView)
            voteAverageView = itemView.findViewById(R.id.ratingView)
            genreView = itemView.findViewById(R.id.genreView)
            languageView = itemView.findViewById(R.id.languageView)
            itemViewGroup = itemView.findViewById(R.id.itemMovieByIDViewGroup)


            titleView.text = item.title
            voteAverageView.text = item.voteAverage.toString()
            genreView.text = item.genre.toString()
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
        }
    }
}