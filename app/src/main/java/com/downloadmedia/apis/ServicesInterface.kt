package com.downloadmedia.apis

import com.downloadmedia.remoteDataModel.MediaDataModel
import io.reactivex.Single
import retrofit2.http.GET

interface ServicesInterface {

  @GET("movies")
  fun getMedia(): Single<MutableList<MediaDataModel>>
}