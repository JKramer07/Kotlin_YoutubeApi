package com.geek.kotlin_youtubeapi.di

import com.geek.kotlin_youtubeapi.ui.playlist_activity.PlaylistViewModel
import com.geek.kotlin_youtubeapi.ui.playlist_details_activity.PlaylistDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { PlaylistDetailsViewModel(get()) }
}