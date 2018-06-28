package com.gka.myflickclient.ui.main

import com.gka.myflickclient.generals.const
import com.gka.myflickclient.generals.prefs
import com.gka.myflickclient.models.Photo
import com.gka.myflickclient.network.NetworkService
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter @Inject constructor(private var service: NetworkService) : MvpBasePresenter<MainView>() {

    private var curPage = 1
    private var isLoading = false

    private var query: String? = null

    private var historyAdapter = HistoryAdapter(prefs.searchHistory, this)

    fun onAttached() {
        loadPhotos()
        initHistoryList()

    }

    private fun loadPhotos() {
        service.photosSearch(const.methodPhotosSearch,
                const.apiKey, query, const.media,
                const.perPage, curPage, const.format, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showProgress() }
                .doOnTerminate { view?.hideProgress() }
                .subscribe({ result ->
                    view?.photosAdapter()?.addItems(result.photos?.photo as ArrayList<Photo>)
                }, { error ->
                    view?.showError(error.message ?: "")
                })
    }


    private fun searchAction(query: String) {
        if (query.isEmpty()) {
            this.query = null
        }

        this.query = query
        curPage = 1
        isLoading = false

        loadPhotos()
    }

    fun loadMorePhotos() {
        if (isLoading) return

        curPage++
        loadPhotos()
    }

    fun onSearchFocusChanged(hasFocus: Boolean) {
        if (hasFocus) {
            historyAdapter.updateItems(prefs.searchHistory)
            view?.showSearch()
        } else {
            view?.hideSearch()
        }
    }

    private fun initHistoryList() {
        view?.initHistoryList(historyAdapter)
    }

    fun onSearchBtnClick(query: String) {
        view?.photosAdapter()?.clearItems()

        searchAction(query)
        saveToHistory(query)

        view?.hideSearch()
    }


    private fun saveToHistory(keyWord: String) {
        if (keyWord.isEmpty()) return

        val list = prefs.searchHistory

        list.remove(keyWord)
        list.add(0, keyWord)

        prefs.searchHistory = list
    }

    fun historyItemClicked(historyItem: String) {
        view?.setSearchText(historyItem)
        onSearchBtnClick(historyItem)
    }

    fun onCancelClicked() {
        view?.hideCancelBtn()
        view?.setSearchText("")
        view?.clearSearchFocus()
        view?.photosAdapter()?.clearItems()
        searchAction("")
    }

}