package com.geek.kotlin_youtubeapi.remote

import com.geek.kotlin_youtubeapi.model.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApi {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("maxResult") maxResult: Int
    ) : Call<PlayList>

    @GET("playlists")
    fun getPlaylistDetails(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("id") id: String
    ) : Call<PlayList>
}