package com.santos.jukebox.client.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.santos.jukebox.R
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.ui.adapter.SectionMusicAdapter
import com.santos.jukebox.client.ui.state.StateClient
import com.santos.jukebox.client.viewmodel.ClientViewModel
import com.santos.jukebox.databinding.BottomDialogMusicBinding
import com.santos.jukebox.databinding.FragmentMenuMusicBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MenuMusicFragment : Fragment() {
    private lateinit var binding: FragmentMenuMusicBinding
    private val viewModel:
            ClientViewModel by viewModel()

    private val adapterMusic by lazy {
        SectionMusicAdapter(
            listener = { music ->
                configBottomSheatDialog(music)
            }).apply {
            listMusic
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        setupListeners()
    }

    private fun setupListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                text?.let { viewModel.filterResults(it) }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let { viewModel.filterResults(it) }
                return true
            }

        })
    }

    private fun setupObservables() {
        viewModel.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is StateClient.Loading -> {
                    binding.pbLoad.isVisible = true
                    binding.tvEmptyMusics.isVisible = false
                }

                is StateClient.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.pbLoad.isVisible = false
                }
                is StateClient.SuccessListMusic -> {
                    binding.recyclerSection.isVisible = true
                    binding.tvEmptyMusics.isVisible = false
                    adapterMusic.listMusic = it.listMusic
                    binding.recyclerSection.adapter = adapterMusic
                    binding.pbLoad.isVisible = false
                }
                is StateClient.SuccessEmptyList -> {
                    binding.recyclerSection.isVisible = false
                    binding.pbLoad.isVisible = false
                    binding.tvEmptyMusics.isVisible = true
                }
            }
        })
    }

    private fun configBottomSheatDialog(music: Music) {
        val binding = BottomDialogMusicBinding.bind(
            layoutInflater.inflate(R.layout.bottom_dialog_music, null)
        )
        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
        bottomSheetDialog.setContentView(binding.root)

        binding.nameMusic.text = music.title
        binding.subtitleAuthor.text = music.author
        binding.btnClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        binding.btnAddQueue.setOnClickListener {
            viewModel.addMusicQueue(true, music)
            binding.btnAddQueue.text = getString(R.string.message_music_add)
            binding.btnAddQueue.isEnabled = false
        }
        bottomSheetDialog.show()
    }
}