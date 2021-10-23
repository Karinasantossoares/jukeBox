package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.santos.jukebox.client.ui.state.StateSuggestion
import com.santos.jukebox.client.viewmodel.SuggestionViewModel
import com.santos.jukebox.databinding.FragmentRecommendedMusicBinding
import com.santos.jukebox.establishment.ui.adapter.RecommendedMusicAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RecommendedMusicFragment : Fragment() {
    private lateinit var binding: FragmentRecommendedMusicBinding
    private val viewModelRegister:
            SuggestionViewModel by sharedViewModel()

    private val adapter by lazy { RecommendedMusicAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecommendedMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        binding.recyclerRecommended.adapter = adapter
    }


    private fun setupObservables() {
        viewModelRegister.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                StateSuggestion.Loading -> {
                    binding.pbLoad.isVisible = true
                }
                is StateSuggestion.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.pbLoad.isVisible = false

                }
                is StateSuggestion.SuccessListMusic -> {
                    binding.pbLoad.isVisible = false
                    adapter.listRecommendedMusic = it.listMusic

                }
            }
        })
    }


}