package com.geek.kotlin_youtubeapi.ui.playlist_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geek.kotlin_youtubeapi.model.PlayList
import com.geek.kotlin_youtubeapi.repository.Repository

class PlaylistViewModel(private val repository: Repository) : ViewModel() {

    fun getPlaylist(): LiveData<PlayList> {
        return repository.createCall()
    }

}