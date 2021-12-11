package com.financial.financialproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Context, val movieList : MutableList<MovieItem>) : RecyclerView.Adapter<MyAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieName : TextView = view.findViewById(R.id.tv_movie_name)
        val imageUrl : ImageView = view.findViewById(R.id.image_view)
        val desc : TextView = view.findViewById(R.id.tv_movie_desc)


        fun bindItems(item: MovieItem) {
            movieName.setText(item.getName())
            desc.setText(item.mDesc)
            val url = "https://image.tmdb.org/t/p/original" + item.mImageUrl
            Picasso.with(itemView.context).load(url).fit().centerInside().into(imageUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(movieList.get(position))
    }
}