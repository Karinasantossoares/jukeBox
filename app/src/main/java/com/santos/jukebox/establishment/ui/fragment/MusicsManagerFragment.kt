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
import com.santos.jukebox.establishment.ui.adapter.MusicByTypesAdapter
import com.santos.jukebox.establishment.ui.state.StateListMusic
import com.santos.jukebox.establishment.viewmodel.ManagerMusicViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MusicsManagerFragment : Fragment() {
    private lateinit var binding: FragmentManagerMenuMusicBinding
    private val viewModel:
            ManagerMusicViewModel by viewModel()

    private val adapter by lazy { MusicByTypesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManagerMenuMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerSection.adapter = adapter
        binding.btnSeeQueue.isVisible = true
        binding.btnRegisterMusic.isVisible = true
        setupListeners()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                StateListMusic.Loading -> {
                    binding.pbLoad.isVisible = true
                }
                is StateListMusic.ShowMessage -> {
                    binding.pbLoad.isVisible = false

                }
                is StateListMusic.SuccessListMusic -> {
                    binding.pbLoad.isVisible = false
                    adapter.listMusic = it.musics
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSeeQueue.setOnClickListener {

        }

        binding.btnRegisterMusic.setOnClickListener {
            findNavController().navigate(R.id.action_to_register)
        }

        adapter.editListener = {
            viewModel.tapOnEdit(it)
        }

        adapter.deleteListener = {
            viewModel.tapOnDelete(it)
        }
    }
}