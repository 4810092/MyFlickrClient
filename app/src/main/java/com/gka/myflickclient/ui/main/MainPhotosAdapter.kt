package com.gka.myflickclient.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gka.myflickclient.R
import com.gka.myflickclient.models.Photo
import kotlinx.android.synthetic.main.item_product.view.*

class MainPhotosAdapter(private var mContext: Context, private var mainView: MainView) : RecyclerView.Adapter<MainPhotosAdapter.ViewHolder>() {

    var data: ArrayList<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = data[position]
        Glide.with(mContext).load(photo.thumb).into(holder.itemView.photo)

        holder.itemView.setOnClickListener { mainView.photoItemClicked(photo) }

    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun addItems(list: ArrayList<Photo>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clearItems() {
        data.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}