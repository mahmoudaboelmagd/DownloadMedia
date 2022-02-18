package com.downloadmedia

import android.os.Build
import com.downloadmedia.ui.adapters.MediaAdapter
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MediaAdapterTest {

  @Test
  fun validateThatListIsEmpty() {
    val adapter = MediaAdapter(listOf())
    Assert.assertEquals(0, adapter.itemCount)
  }

  @Test
  fun validateThatListSizeIsOne() {
    val adapter = MediaAdapter(listOf("item_one"))
    Assert.assertEquals(1, adapter.itemCount)
  }

}