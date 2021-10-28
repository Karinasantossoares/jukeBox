package com.santos.jukebox.client.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.santos.jukebox.R
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.client.ui.state.StateSuggestion
import com.santos.jukebox.client.viewmodel.SuggestionViewModel
import com.santos.jukebox.databinding.FragmentBottomSheetSuggestionBinding
import org.koin.android.viewmodel.ext.android.viewModel


class SuggestionBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomSheetSuggestionBinding
    private val viewModel: SuggestionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetSuggestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.stateLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is StateSuggestion.Loading -> {
                    // binding.pbLoad.isVisible = true
                }
                is StateSuggestion.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.pbLoad.isVisible = false
                }
                is StateSuggestion.SuccessSuggestionMusic -> {
                    binding.btnSend.text = ""
                }
            }
        })
    }

    private fun setupListeners() {
        binding.btnSend.setOnClickListener {
            val suggestionText = binding.edtSuggestion.text.toString()
            val suggestion = SuggestionResponse(
                text = suggestionText
            )
            viewModel.addSuggestionMusic(suggestion)
        }
        binding.btnClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    companion object {
        fun newInstance() = SuggestionBottomSheetFragment()
    }
}

