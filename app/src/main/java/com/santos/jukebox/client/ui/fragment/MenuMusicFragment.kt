package com.santos.jukebox.client.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.santos.jukebox.client.ui.adapter.SectionMusicAdapter
import com.santos.jukebox.client.viewmodel.ClientViewModel
import com.santos.jukebox.databinding.FragmentMenuMusicBinding
import com.santos.jukebox.establishment.ui.adapter.TypeMusicAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MenuMusicFragment : Fragment() {
    private lateinit var binding: FragmentMenuMusicBinding
    private val viewModelClient:
            ClientViewModel by viewModel()

    private val adapter by lazy { TypeMusicAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupListeners() {
//       val adapter = SectionMusicAdapter(
//
//       )
       binding.recyclerSection.adapter
    }
}