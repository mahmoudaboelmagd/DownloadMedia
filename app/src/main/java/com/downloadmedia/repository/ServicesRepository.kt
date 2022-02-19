package com.downloadmedia.repository

import com.downloadmedia.apis.ServicesInterface
import com.downloadmedia.remoteDataModel.MediaDataModel
import io.reactivex.Single
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val servicesInterface: ServicesInterface) {

  fun getMedia(): Single<MutableList<MediaDataModel>> {
    return servicesInterface.getMedia()
  }
}