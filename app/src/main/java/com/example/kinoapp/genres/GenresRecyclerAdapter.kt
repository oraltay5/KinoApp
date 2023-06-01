package com.example.kinoapp.genres

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
import com.example.kinoapp.genres.model.Genres
import com.example.kinoapp.movies.tabs.soon.model.Soon

class GenresRecyclerAdapter(
    private val item: List<Genres>,
    private val onItemClickListener: (Genres) -> Unit,
) : RecyclerView.Adapter<GenresRecyclerAdapter.ViewHolder>() {

    private val viewTypeGrid = 1
    private val viewTypeList = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View

        if (viewType == viewTypeGrid) {
            // Создаем макет для элемента сетки
            view = inflater.inflate(R.layout.item_genres, parent, false)
        } else {
            // Создаем макет для элемента списка
            view = inflater.inflate(R.layout.item_genres, parent, false)
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

//        lateinit var idTextView: TextView
        lateinit var nameTextView: TextView
        lateinit var itemViewGroup: RelativeLayout


        fun bind(item: Genres){
//            idTextView = itemView.findViewById(R.id.idTextView)
            nameTextView = itemView.findViewById(R.id.nameTextView)
            itemViewGroup = itemView.findViewById(R.id.itemGenresViewGroup)


//            idTextView.text = item.id.toString()
            nameTextView.text = item.name

            itemViewGroup.setOnClickListener {
                onItemClickListener(item)
            }
        }
    }
}