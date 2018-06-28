package com.gka.myflickclient.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.gka.myflickclient.R
import com.gka.myflickclient.generals.App
import com.gka.myflickclient.models.Photo
import com.gka.myflickclient.ui.photoView.PhotoActivity
import com.hannesdorfmann.mosby.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<MainView, MainPresenter>(), MainView {

    private var adapter = MainPhotosAdapter(this, this)

    val glm: GridLayoutManager = GridLayoutManager(this, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initSearch()
        setupRecycler()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.onAttached()
    }

    private fun initSearch() {
        search.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            presenter.onSearchFocusChanged(hasFocus)
        }

        search.setOnEditorActionListener(TextView.OnEditorActionListener { _, _, _ ->
            presenter.onSearchBtnClick(search.text.toString())
            return@OnEditorActionListener true
        })


        cancelSearch.setOnClickListener { presenter.onCancelClicked() }
    }

    override fun clearSearchFocus() {
        search.clearFocus()
    }

    override fun hideCancelBtn() {
        cancelSearch.visibility = View.GONE
    }

    override fun showSearch() {
        historyList.visibility = View.VISIBLE
        cancelSearch.visibility = View.VISIBLE
        recycler.visibility = View.GONE
    }

    override fun hideSearch() {
        historyList.visibility = View.GONE
        recycler.visibility = View.VISIBLE
        hideKeyboard()
    }

    override fun initHistoryList(historyAdapter: HistoryAdapter) {
        historyList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        historyList.adapter = historyAdapter
    }

    override fun photosAdapter(): MainPhotosAdapter {
        return adapter
    }

    override fun setSearchText(historyItem: String) {
        search.setText(historyItem)
        search.setSelection(historyItem.length)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(search.windowToken, 0)
    }


    override fun onBackPressed() {

        if (search.isFocused) {
            search.clearFocus()
        } else {
            super.onBackPressed()
        }
    }

    override fun createPresenter(): MainPresenter {
        return App.networkComponent.mainPresenter()
    }

    private fun setupRecycler() {
        recycler.layoutManager = glm
        recycler.adapter = adapter

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) {
                    if (glm.childCount + glm.findFirstVisibleItemPosition() >= glm.itemCount) {
                        presenter.loadMorePhotos()
                    }
                }
            }
        })

    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun photoItemClicked(photo: Photo) {
        val intent = Intent(this, PhotoActivity::class.java)
        intent.putExtra("url", photo.photo)
        startActivity(intent)

    }
}