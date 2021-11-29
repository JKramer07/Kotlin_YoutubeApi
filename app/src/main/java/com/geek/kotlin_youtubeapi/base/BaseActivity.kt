package com.geek.kotlin_youtubeapi.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    protected lateinit var viewBinding: VB
    protected abstract fun inflateViewBinding(): VB

    abstract fun setupUI() // инициализация UI

    abstract fun setupLiveData()

    abstract fun setupClickListener()

    abstract fun checkInternet(context: Context?): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateViewBinding()
        setContentView(viewBinding.root)

        checkInternet(this)
        setupUI()
        setupLiveData()
        setupClickListener()
    }


}