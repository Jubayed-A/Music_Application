package com.example.music_application

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MusicAdapter (private var context : Activity, private var musicList: List<Data>) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView
        val artists : TextView
        val duration : TextView
        val image : ImageView

        init {
            image = itemView.findViewById(R.id.songImg)
            title = itemView.findViewById(R.id.songName)
            artists = itemView.findViewById(R.id.artistName)
            duration = itemView.findViewById(R.id.songDuration)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.custom_song_item,parent,false)
        return MusicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val currentItem = musicList[position]
        holder.title.text = currentItem.title
        holder.artists.text = currentItem.artist.name
        holder.duration.text = currentItem.duration.toString()
        Picasso.get().load(currentItem.album.cover).into(holder.image)
    }
}