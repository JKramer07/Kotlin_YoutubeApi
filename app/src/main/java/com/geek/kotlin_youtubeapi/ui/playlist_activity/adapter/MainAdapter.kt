package com.geek.kotlin_youtubeapi.ui.playlist_activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geek.kotlin_youtubeapi.R
import com.geek.kotlin_youtubeapi.databinding.ItemRvPlaylistBinding
import com.geek.kotlin_youtubeapi.extensions.loadImage
import com.geek.kotlin_youtubeapi.model.Items
import com.geek.kotlin_youtubeapi.model.PlayList

class MainAdapter(
    private val playlist: PlayList,
    private val clickListener: (id: Items) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRvPlaylistBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playlist.items[position])
    }

    override fun getItemCount(): Int {
        return playlist.items.size
    }

    inner class ViewHolder(private var viewBinding: ItemRvPlaylistBinding)
        : RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(playlist: Items) {
            viewBinding.tvVideoName.text = playlist.snippet.title
            viewBinding.ivPlaylist.loadImage(playlist.snippet.thumbnails.default.url)
            viewBinding.tvSeriesAndDuration.text = String.format("${playlist.contentDetails.itemCount}" +
                    " ${itemView.context.getString(R.string.video_series)}")
            itemView.setOnClickListener {
                clickListener(playlist)
            }
        }
    }
}
