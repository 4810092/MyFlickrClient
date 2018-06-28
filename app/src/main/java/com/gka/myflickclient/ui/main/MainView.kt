package com.gka.myflickclient.ui.main

import com.gka.myflickclient.models.Photo
import com.gka.myflickclient.ui.base.BaseMvpView

interface MainView : BaseMvpView {
    fun photosAdapter(): MainPhotosAdapter

    fun photoItemClicked(photo: Photo)
    fun showSearch()
    fun hideSearch()
    fun setSearchText(historyItem: String)
    fun clearSearchFocus()
    fun hideCancelBtn()
    fun initHistoryList(historyAdapter: HistoryAdapter)
}