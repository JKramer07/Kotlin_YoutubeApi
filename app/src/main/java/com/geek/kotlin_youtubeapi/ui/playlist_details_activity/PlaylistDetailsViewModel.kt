package com.geek.kotlin_youtubeapi.ui.playlist_details_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geek.kotlin_youtubeapi.model.PlayList
import com.geek.kotlin_youtubeapi.repository.Repository

class PlaylistDetailsViewModel(private val repository: Repository) : ViewModel() {

    fun getPlaylistDetails(): LiveData<PlayList> {
        return repository.createDetails()
    }
}