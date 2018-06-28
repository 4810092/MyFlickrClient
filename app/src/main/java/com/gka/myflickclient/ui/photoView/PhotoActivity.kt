package com.gka.myflickclient.ui.photoView

import android.os.Bundle
import com.bumptech.glide.Glide
import com.gka.myflickclient.R
import com.gka.myflickclient.generals.App
import com.hannesdorfmann.mosby.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : MvpActivity<PhotoView, PhotoPresenter>() {
    override fun createPresenter(): PhotoPresenter {
        return App.networkComponent.photoPresenter()
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        url = intent.getStringExtra("url")

        Glide.with(this).load(url).into(photo)
    }
}
