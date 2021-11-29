package com.geek.kotlin_youtubeapi.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(image: String) {
    Glide.with(context)
        .load(image)
        .centerCrop()
        .into(this)
}