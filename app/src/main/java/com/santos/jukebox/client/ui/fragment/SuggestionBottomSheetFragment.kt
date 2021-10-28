package com.santos.jukebox.client.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.santos.jukebox.R
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.client.ui.state.StateSuggestion
import com.santos.jukebox.client.viewmodel.SuggestionViewModel
import com.santos.jukebox.databinding.BottomSheetSuggestionBinding
import org.koin.android.viewmodel.ext.android.viewModel


class SuggestionBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetSuggestionBinding
    private val viewModel: SuggestionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetSuggestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservables()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = true
        dialog.behavior.isHideable = true
        dialog.behavior.isDraggable = true
        return dialog
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
                   dismiss()
                }
            }
        })
    }

    private fun setupListeners() {
        binding.btnSend.setOnClickListener {
            val suggestionText = binding.edtSuggestion.text.toString()
            val suggestion = SuggestionResponse(
                nameMusic = suggestionText
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

