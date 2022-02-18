package com.downloadmedia.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.downloadmedia.databinding.FragmentHomeBinding
import com.downloadmedia.ui.adapters.MediaAdapter


class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    if (this::binding.isInitialized) {
      binding
    } else {
      binding = FragmentHomeBinding.inflate(inflater, container, false)

    }

    initAdapter()

    return binding.root
  }

  private fun initAdapter() {
    val mediaAdapter = MediaAdapter(listOf("a", "b"))
    binding.recyclerMedia.adapter = mediaAdapter
    binding.recyclerMedia.layoutManager = LinearLayoutManager(activity)
  }

}