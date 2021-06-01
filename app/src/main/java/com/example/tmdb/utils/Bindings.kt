package com.example.tmdb.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tmdb.BuildConfig
import com.example.tmdb.R


interface BindAdapter<T> {
    fun setData(data: T)
}

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindAdapter<*>) {
        data?.let { (recyclerView.adapter as? BindAdapter<T>)?.setData(it) }
    }
}

@BindingAdapter("loadUrlImage")
fun loadUrlImage(image: AppCompatImageView, url: String?) {
    Glide.with(image.context)
        .load(BuildConfig.IMAGE_BASE_URL + url) // TODO: set full image url into business object
        .placeholder(R.drawable.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(image)
}