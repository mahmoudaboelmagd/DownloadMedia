package com.downloadmedia.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.downloadmedia.R.layout

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
  }
}