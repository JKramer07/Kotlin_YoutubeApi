package com.geek.kotlin_youtubeapi.ui.playlist_activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.geek.kotlin_youtubeapi.base.BaseActivity
import com.geek.kotlin_youtubeapi.databinding.ActivityPlaylistBinding
import com.geek.kotlin_youtubeapi.extensions.showToast
import com.geek.kotlin_youtubeapi.extensions.visible
import com.geek.kotlin_youtubeapi.model.Items
import com.geek.kotlin_youtubeapi.model.PlayList
import com.geek.kotlin_youtubeapi.ui.playlist_activity.adapter.MainAdapter
import com.geek.kotlin_youtubeapi.ui.playlist_details_activity.PlaylistDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding>() {

    companion object {const val PLAYLIST = "playlist"}

    private lateinit var playlist: PlayList

    private val viewModel: PlaylistViewModel by viewModel()

    private val myAdapter: MainAdapter by lazy {
        MainAdapter(playlist, this::clickListener)
    }

    override fun setupUI() {

    }

    private fun initRecycler() {
        viewBinding.rvPlaylist.apply {
            layoutManager = LinearLayoutManager(this@PlaylistActivity,
            LinearLayoutManager.VERTICAL, false)
            adapter = this@PlaylistActivity.myAdapter }
        myAdapter.notifyDataSetChanged()
    }

    override fun setupLiveData() {
        viewModel.getPlaylist().observe(this) {
            playlist = it
            initRecycler()
        }
    }

    override fun setupClickListener() {
    }

    override fun checkInternet(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            } else {
                viewBinding.noInternet.visible()
                showToast("No internet!")
            }
        }
        return false
    }

    override fun inflateViewBinding(): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)
    }

    private fun clickListener(id: Items) {
        Intent(this, PlaylistDetailsActivity::class.java).apply {
            putExtra(PLAYLIST, id.id)
            startActivity(this)
        }
    }
}