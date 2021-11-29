package com.geek.kotlin_youtubeapi.di

import com.geek.kotlin_youtubeapi.repository.Repository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    factory { Repository(get()) }
}