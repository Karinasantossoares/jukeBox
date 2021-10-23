package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.santos.jukebox.databinding.FragmentGraphBinding
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.adapter.ItemMusicAdapter
import com.santos.jukebox.establishment.ui.state.StateGraph
import com.santos.jukebox.establishment.viewmodel.GraphViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class GraphFragment : Fragment() {
    private lateinit var binding: FragmentGraphBinding
    private val viewModel:
            GraphViewModel by viewModel()

    private val adapter by lazy {
        ItemMusicAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is StateGraph.Loading -> {
                    binding.pbLoad.isVisible = true
                }
                is StateGraph.ShowMessageId -> {
                    Toast.makeText(requireContext(), getString(it.idMusic), Toast.LENGTH_SHORT)
                        .show()
                }
                is StateGraph.SuccessGraph -> {
                    binding.graphChart.chartData = it.chartData
                }
                is StateGraph.SuccessTopMusics -> {
                    adapter.listNameMusic =
                        it.musics.map { RegisterMusicEstablishment(title = it.title) }
                }
                StateGraph.SuccessGraphEmpty -> {

                }
                StateGraph.SuccessTopMusicsEmpty -> {

                }
            }
        }
    }

    private fun setupListeners() {
        binding.rvListMusics.adapter = adapter

    }
}