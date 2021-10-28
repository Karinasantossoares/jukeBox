package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.santos.jukebox.client.ui.state.StateRecommended
import com.santos.jukebox.databinding.FragmentRecommendedMusicBinding
import com.santos.jukebox.establishment.ui.adapter.RecommendedMusicAdapter
import com.santos.jukebox.establishment.viewmodel.RecommendedMusicViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RecommendedMusicFragment : Fragment() {
    private lateinit var binding: FragmentRecommendedMusicBinding
    private val viewModelRecommendedMusic:
            RecommendedMusicViewModel by viewModel()

    private val adapter by lazy {
        RecommendedMusicAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendedMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setupObservables()
    }


    private fun setupObservables() {
        viewModelRecommendedMusic.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                StateRecommended.Loading -> {
                    binding.pbLoad.isVisible = true
                }
                is StateRecommended.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.pbLoad.isVisible = false

                }
                is StateRecommended.SuccessListMusic -> {
                    binding.recyclerRecommended.adapter = adapter
                    binding.pbLoad.isVisible = false
                    adapter.listRecommendedMusic = it.listMusic

                }
            }
        })
    }

    private fun setupListener() {
        adapter.listenerRemoveMusic = {
            it.id?.let { it1 -> viewModelRecommendedMusic.deleteRecommendedMusic(it1) }
        }
    }
}