package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.santos.jukebox.R
import com.santos.jukebox.databinding.FragmentManagerMenuMusicBinding
import com.santos.jukebox.establishment.ui.adapter.MusicByTypesAdapter
import com.santos.jukebox.establishment.ui.fragment.RegisterMusicFragment.Companion.EXTRA_KEY_MUSIC
import com.santos.jukebox.establishment.ui.state.EventListMusic
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
                    binding.tvEmptyMusics.isVisible = false
                    binding.pbLoad.isVisible = true
                }
                is StateListMusic.ShowMessage -> {
                    binding.pbLoad.isVisible = false

                }
                is StateListMusic.ShowMessageId -> {
                    Toast.makeText(requireContext(), getString(it.idMusic), Toast.LENGTH_SHORT).show()

                }
                is StateListMusic.SuccessListMusic -> {
                    binding.recyclerSection.isVisible = true
                    binding.pbLoad.isVisible = false
                    binding.tvEmptyMusics.isVisible = false
                    adapter.listMusic = it.musics
                }
                StateListMusic.SuccessEmptyList -> {
                    binding.pbLoad.isVisible = false
                    binding.tvEmptyMusics.isVisible = true
                    binding.recyclerSection.isVisible = false
                }
            }
        }

        viewModel.actionLiveData.observe(viewLifecycleOwner) { eventMusic ->
            when (eventMusic) {
                is EventListMusic.EditMusic -> {
                    findNavController().navigate(R.id.action_to_register, Bundle().apply {
                        putParcelable(EXTRA_KEY_MUSIC, eventMusic.music)
                    })
                }
                is EventListMusic.DeleteMusic -> {
                    AlertDialog.Builder(requireContext())
                        .setMessage(getString(R.string.message_delete_music))
                        .setTitle(getString(R.string.alert))
                        .setPositiveButton(
                            getString(R.string.yes)
                        ) { p0, p1 ->
                            viewModel.deleteMusic(eventMusic.music)
                        }
                        .create()
                        .show()
                }
            }
        }
    }

    private fun setupListeners() {
        binding.btnSeeQueue.setOnClickListener {
            findNavController().navigate(R.id.action_to_queue)
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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                text?.let { viewModel.filterResultsSearchView(it) }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let { viewModel.filterResultsSearchView(it) }
                return true
            }

        })
    }
}