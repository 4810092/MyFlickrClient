package com.gka.myflickclient.ui.base

import com.hannesdorfmann.mosby.mvp.MvpView

interface BaseMvpView : MvpView {
    fun showProgress()

    fun hideProgress()

    fun showError(message: String)

}