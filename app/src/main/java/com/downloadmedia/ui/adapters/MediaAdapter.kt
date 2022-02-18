package com.downloadmedia.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.downloadmedia.databinding.ItemMediaBinding


class MediaAdapter(
  private val mediaList: List<String>
) : RecyclerView.Adapter<MediaAdapter.ArticleViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemMediaBinding.inflate(
      inflater,
      parent,
      false
    )
    return ArticleViewHolder(binding)
  }

  class ArticleViewHolder internal constructor(binding: ItemMediaBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

  }

  override fun getItemCount() = mediaList.size
}