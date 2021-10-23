package com.santos.jukebox.establishment.ui.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.santos.jukebox.R
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
                    binding.pbLoad.isVisible = false
                    Toast.makeText(requireContext(), getString(it.idMusic), Toast.LENGTH_SHORT)
                        .show()
                }
                is StateGraph.SuccessGraph -> {
                    binding.pbLoad.isVisible = false
                    setDataPieChart(it.chartData.toMutableList())
                }
                is StateGraph.SuccessTopMusics -> {
                    binding.pbLoad.isVisible = false
                    adapter.listNameMusic =
                        it.musics.map { RegisterMusicEstablishment(title = it.title) }
                }
                StateGraph.SuccessGraphEmpty -> {
                    binding.pbLoad.isVisible = false

                }
                StateGraph.SuccessTopMusicsEmpty -> {
                    binding.pbLoad.isVisible = false

                }
            }
        }
    }

    private fun setupListeners() {
        binding.rvListMusics.adapter = adapter

    }

    private fun setDataPieChart(value: MutableList<PieEntry>) {
        val dataset = PieDataSet(value, getString(R.string.text_graph))
        dataset.sliceSpace = 100F
        binding.graphChart.legend.isWordWrapEnabled = true
        binding.graphChart.setEntryLabelColor(Color.BLACK)
        binding.graphChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD)
        binding.graphChart.centerText = ""
        dataset.label = ""
        dataset.setColors(
            intArrayOf(
                R.color.grapy_1,
                R.color.grapy_2,
                R.color.grapy_3,
                R.color.grapy_4,
                R.color.grapy_5,
                R.color.grapy_6
            ), requireContext())
        val data = PieData(dataset)
        binding.graphChart.data = data
        binding.graphChart.invalidate()
    }
}