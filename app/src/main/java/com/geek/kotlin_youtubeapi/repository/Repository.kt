package com.geek.kotlin_youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geek.kotlin_youtubeapi.BuildConfig
import com.geek.kotlin_youtubeapi.`object`.Constant
import com.geek.kotlin_youtubeapi.model.PlayList
import com.geek.kotlin_youtubeapi.remote.YoutubeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository (private val youtubeApi: YoutubeApi){

    fun createCall(): LiveData<PlayList> {
        val data = MutableLiveData<PlayList>()
        youtubeApi.getPlaylists(Constant.PART, Constant.CHANNEL_ID, BuildConfig.API_KEY, Constant.MAX_RESULT)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    print(t.stackTrace)
                    // 404 - не найдено // 403 - токен истек //401 - нет доступа
                }

            })
        return data
    }

    fun createDetails(): LiveData<PlayList> {
        val detailsData = MutableLiveData<PlayList>()
        youtubeApi.getPlaylistDetails(Constant.PART, Constant.ID, Constant.CHANNEL_ID, BuildConfig.API_KEY)
            .enqueue(object : Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                    if (response.isSuccessful && response.body() != null) {
                        detailsData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    print(t.stackTrace)
                }
            })
        return detailsData
    }

}