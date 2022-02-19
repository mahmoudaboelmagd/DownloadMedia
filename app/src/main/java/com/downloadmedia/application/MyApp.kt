package com.downloadmedia.application

import android.app.Application

class MyApp : Application() {
  val appComponent = DaggerApplicationComponent.create()
}