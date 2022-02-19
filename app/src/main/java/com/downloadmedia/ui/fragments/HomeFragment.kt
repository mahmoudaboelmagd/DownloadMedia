package com.downloadmedia.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.downloadmedia.R
import com.downloadmedia.databinding.FragmentHomeBinding
import com.downloadmedia.remoteDataModel.MediaDataModel
import com.downloadmedia.ui.adapters.MediaAdapter
import javax.inject.Inject


class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding

  lateinit var mediaAdapter: MediaAdapter

  @Inject
  lateinit var homeViewModel: HomeViewModel

  private var downloadClicked = MutableLiveData<MediaDataModel>()

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

    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    observers()

    homeViewModel.getMediaList()

    initAdapter()
  }

  private fun initAdapter() {
    mediaAdapter = MediaAdapter()
    mediaAdapter.setOnItemSelectedListener(downloadClicked)
    binding.recyclerMedia.adapter = mediaAdapter
    binding.recyclerMedia.layoutManager = LinearLayoutManager(activity)
  }

  private fun observers(){
    homeViewModel.mediaList.observe(viewLifecycleOwner) { media ->
      mediaAdapter.setData(media)
    }

    homeViewModel.downloadStatus.observe(viewLifecycleOwner) { downloadStatus ->
      if (downloadStatus == 1) {
        mediaAdapter.setProgress(false)
        Toast.makeText(context, getString(R.string.donwloaded_successfully), Toast.LENGTH_SHORT).show()
      } else if (downloadStatus == 2) {
        mediaAdapter.setProgress(false)
        Toast.makeText(context, getString(R.string.download_failed), Toast.LENGTH_SHORT).show()
      }
    }

    downloadClicked.observe(viewLifecycleOwner) { mediaModel ->
      if (mediaModel != null){
        mediaAdapter.setProgress(true)
        homeViewModel.downloadMedia(requireContext(), mediaModel.url, mediaModel.type)
      }
    }

  }

}