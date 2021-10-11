package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.santos.jukebox.R
import com.santos.jukebox.databinding.FragmentManagerMenuMusicBinding
import com.santos.jukebox.databinding.FragmentQueueMusicBinding
import com.santos.jukebox.establishment.ui.adapter.ItemQueueMusicAdapter
import com.santos.jukebox.establishment.ui.adapter.MusicByTypesAdapter
import com.santos.jukebox.establishment.ui.state.StateListMusic
import com.santos.jukebox.establishment.ui.state.StateQueueMusic
import com.santos.jukebox.establishment.viewmodel.ManagerMusicViewModel
import com.santos.jukebox.establishment.viewmodel.QueueMusicViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MusicsQueueFragment : Fragment() {
    private lateinit var binding: FragmentQueueMusicBinding
    private val viewModel:
            QueueMusicViewModel by viewModel()

    private val adapter by lazy { ItemQueueMusicAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQueueMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvList.adapter = adapter
        setupListeners()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                StateQueueMusic.Loading -> binding.pbLoad.isVisible = true
                is StateQueueMusic.ShowMessage -> {
                    binding.pbLoad.isVisible = false
                }
                is StateQueueMusic.SuccessListMusic -> {
                    binding.rvList.isVisible = true
                    binding.tvEmpty.isVisible = false
                    binding.pbLoad.isVisible = false
                    adapter.listNameMusic = it.musics
                }
                StateQueueMusic.EmptyList -> {
                    binding.pbLoad.isVisible = false
                    binding.rvList.isVisible = false
                    binding.tvEmpty.isVisible = true
                }
            }
        }
    }

    private fun setupListeners() {
        adapter.tapFinishListener = {
            viewModel.markAsFinish(it.id)
        }
    }
}