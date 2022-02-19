package com.downloadmedia.ui.fragments

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.cnrylmz.zionfiledownloader.FILE_TYPE
import com.cnrylmz.zionfiledownloader.ZionDownloadFactory
import com.cnrylmz.zionfiledownloader.ZionDownloadListener
import com.downloadmedia.remoteDataModel.MediaDataModel
import com.downloadmedia.repository.ServicesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(
  application: Application
) : AndroidViewModel(application),
  LifecycleObserver {

  val mediaList: MutableLiveData<MutableList<MediaDataModel>> = MutableLiveData()

  @Inject
  lateinit var servicesRepository: ServicesRepository

  private lateinit var disposable: Disposable

  var compositeDisposable = CompositeDisposable()

  var errorThrowable = MutableLiveData<Throwable>()

  var downloadStatus = MutableLiveData<Int>(0)

  fun getMediaList() {

    disposable = servicesRepository.getMedia()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ media ->
        run {
          mediaList.value = media
        }
      }, { throwable ->
        run {
          errorThrowable.value = throwable
        }
      })

    compositeDisposable.add(disposable)


//    mediaList.value = mutableListOf(
//      MediaDataModel(1, "VIDEO", "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4", "Video 1", false),
//      MediaDataModel(2, "VIDEO", "https://bestvpn.org/html5demos/assets/dizzy.mp4", "Video 2", false),
//      MediaDataModel(3, "PDF", "https://kotlinlang.org/docs/kotlin-reference.pdf", "PDF 3", false),
//      MediaDataModel(5, "PDF", "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf", "PDF 5", false),
//    )
  }

  fun downloadMedia(context: Context, mediaUrl: String, type: String) {
    try {
      val factory = ZionDownloadFactory(
        context,
        mediaUrl,
        mediaUrl.substring(mediaUrl.lastIndexOf("/"), mediaUrl.lastIndexOf("."))
      )
      var fileType = FILE_TYPE.PDF
      if (type == "VIDEO")
        fileType = FILE_TYPE.VIDEO
      if (type == "MP3")
        fileType = FILE_TYPE.MP3
      if (type == "IMAGE")
        fileType = FILE_TYPE.IMAGE
      val downloadFile = factory.downloadFile(fileType)
      downloadFile.start(object : ZionDownloadListener {
        override fun OnSuccess(dataPath: String) {
          downloadStatus.value = 1
        }

        override fun OnFailed(message: String) {
          downloadStatus.value = 2
        }

        override fun OnPaused(message: String) {}
        override fun OnPending(message: String) {}
        override fun OnBusy() {}
      })
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.dispose()
  }

}