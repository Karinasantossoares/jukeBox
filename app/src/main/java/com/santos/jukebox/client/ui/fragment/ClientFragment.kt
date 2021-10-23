package com.santos.jukebox.client.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.santos.jukebox.R
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.ui.adapter.SectionMusicAdapter
import com.santos.jukebox.client.ui.state.StateClient
import com.santos.jukebox.client.viewmodel.ClientViewModel
import com.santos.jukebox.databinding.BottomDialogMusicBinding
import com.santos.jukebox.databinding.FragmentClientBinding
import org.koin.android.viewmodel.ext.android.viewModel


class ClientFragment : Fragment() {
    private lateinit var binding: FragmentClientBinding
    private val viewModel:
            ClientViewModel by viewModel()

    private val bindingBottomSheet by lazy {
        BottomDialogMusicBinding.bind(
            layoutInflater.inflate(R.layout.bottom_dialog_music, null)
        )
    }

    private val bottomSheetDialog by lazy {
        BottomSheetDialog(requireContext(), R.style.BottomSheetTheme).apply {
            setContentView(
                bindingBottomSheet.root
            )
        }
    }


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
        binding = FragmentClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSuggestionMusic.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_suggestionFragment)
        }
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
                StateClient.SuccessRequestedMusic -> {
                    bottomSheetDialog.dismiss()
                    bindingBottomSheet.edtRequester.setText("")
                }
            }
        })
    }

    private fun configBottomSheatDialog(music: Music) {

        bindingBottomSheet.nameMusic.text = music.title
        bindingBottomSheet.subtitleAuthor.text = music.author

        bindingBottomSheet.btnClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bindingBottomSheet.edtRequester.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE
                || actionId == EditorInfo.IME_ACTION_GO
                || actionId == EditorInfo.IME_ACTION_NEXT
            ) {
                actionAddQueueMusic(music)
                return@OnEditorActionListener true
            }
            false
        })
        bindingBottomSheet.btnAddQueue.setOnClickListener {
            actionAddQueueMusic(music)
        }
        bottomSheetDialog.show()
    }

    private fun actionAddQueueMusic(music: Music) {
        viewModel.addMusicQueue(true, music.apply {
            requestName = bindingBottomSheet.edtRequester.text.toString()
        })
    }
}

