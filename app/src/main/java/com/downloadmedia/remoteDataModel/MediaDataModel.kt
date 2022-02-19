package com.downloadmedia.remoteDataModel

data class MediaDataModel(
    val id: Int,
    val type: String,
    val url: String,
    val name: String,
    var isDownloaded: Boolean
)
