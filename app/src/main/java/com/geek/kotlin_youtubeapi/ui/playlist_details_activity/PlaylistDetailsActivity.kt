package com.geek.kotlin_youtubeapi.ui.playlist_details_activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.geek.kotlin_youtubeapi.base.BaseActivity
import com.geek.kotlin_youtubeapi.databinding.ActivityPlaylistDetailsBinding
import com.geek.kotlin_youtubeapi.extensions.showToast
import com.geek.kotlin_youtubeapi.extensions.visible
import com.geek.kotlin_youtubeapi.model.Items
import com.geek.kotlin_youtubeapi.model.PlayList
import com.geek.kotlin_youtubeapi.ui.playlist_activity.PlaylistActivity.Companion.PLAYLIST
import com.geek.kotlin_youtubeapi.ui.playlist_activity.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailsActivity : BaseActivity<ActivityPlaylistDetailsBinding>() {

    private lateinit var playlist: PlayList

    private val viewModel: PlaylistDetailsViewModel by viewModel()

    private val myAdapter: MainAdapter by lazy {
        MainAdapter(playlist, this::clickListener)
    }

    override fun setupUI() {
        showToast(intent.getStringExtra(PLAYLIST).toString())
    }

    override fun setupLiveData() {
        viewModel.getPlaylistDetails().observe(this) {
            playlist = it
            initRecycler()
        }
    }

    private fun initRecycler() {
        viewBinding.rvPlaylistDetails.apply {
            layoutManager = LinearLayoutManager(this@PlaylistDetailsActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter = myAdapter }
        myAdapter.notifyDataSetChanged()
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

    override fun inflateViewBinding(): ActivityPlaylistDetailsBinding {
        return ActivityPlaylistDetailsBinding.inflate(layoutInflater)
    }

    override fun setupClickListener() {

    }

    private fun clickListener(id: Items) {

    }
}
