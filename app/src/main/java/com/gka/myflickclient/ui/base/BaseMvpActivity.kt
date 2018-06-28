package com.gka.myflickclient.ui.base

import android.support.v7.app.AppCompatActivity
import com.gka.myflickclient.generals.App
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView

abstract class BaseMvpActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>() {
    fun getApp(): App {
        return application as App
    }

    fun getActivityContext(): AppCompatActivity {
        return this
    }
}