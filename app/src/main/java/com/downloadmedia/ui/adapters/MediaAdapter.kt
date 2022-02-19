package com.downloadmedia.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.downloadmedia.R
import com.downloadmedia.databinding.ItemMediaBinding
import com.downloadmedia.remoteDataModel.MediaDataModel


class MediaAdapter : RecyclerView.Adapter<MediaAdapter.ArticleViewHolder>() {

  val mediaList: MutableList<MediaDataModel> = ArrayList()

  private var downloadClicked = MutableLiveData<MediaDataModel>()

  private var currentPos = 0

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = ItemMediaBinding.inflate(
      inflater,
      parent,
      false
    )
    return ArticleViewHolder(binding)
  }

  class ArticleViewHolder internal constructor(val binding: ItemMediaBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

    holder.binding.fileName.text = mediaList[position].name

    holder.binding.downloadImg.setOnClickListener {
      currentPos = position
      downloadClicked.value = mediaList[position]
    }

    if (mediaList[position].isDownloaded){
      holder.binding.progressDownload.visibility = View.VISIBLE
    }else{
      holder.binding.progressDownload.visibility = View.INVISIBLE
    }

    if (mediaList[position].type == "PDF"){
      holder.binding.fileType.setBackgroundResource(R.drawable.ic_pdf)
    }else{
      holder.binding.fileType.setBackgroundResource(R.drawable.ic_video)
    }

  }

  fun setProgress(isDownload:Boolean){
    mediaList[currentPos].isDownloaded = isDownload
    notifyDataSetChanged()
  }

  fun setData(media: List<MediaDataModel>) {
    mediaList.addAll(media)
  }

  override fun getItemCount() = mediaList.size


  fun setOnItemSelectedListener(downloadClicked: MutableLiveData<MediaDataModel>) {
    this.downloadClicked = downloadClicked
  }

}