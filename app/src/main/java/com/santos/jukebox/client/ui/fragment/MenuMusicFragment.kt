package com.santos.jukebox.client.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.santos.jukebox.client.ui.adapter.SectionMusicAdapter
import com.santos.jukebox.client.ui.state.StateClient
import com.santos.jukebox.client.viewmodel.ClientViewModel
import com.santos.jukebox.databinding.FragmentMenuMusicBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MenuMusicFragment : Fragment() {
    private lateinit var binding: FragmentMenuMusicBinding
    private val viewModelClient:
            ClientViewModel by viewModel()

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
    }

    private fun setupObservables() {
        viewModelClient.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is StateClient.Loading -> {

                }
                is StateClient.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is StateClient.SuccessListMusic -> {
                    val adapter = SectionMusicAdapter(
                        listener = {}
                    ).apply {
                        listMusic = it.listMusic
                    }
                    binding.recyclerSection.adapter = adapter
                }
            }
        })
    }
}