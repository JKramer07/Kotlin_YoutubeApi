package com.geek.kotlin_youtubeapi.di

import com.geek.kotlin_youtubeapi.remote.networkModules

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModules
)