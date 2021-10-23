package com.santos.jukebox.client.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.client.viewmodel.SuggestionViewModel
import com.santos.jukebox.databinding.FragmentSuggestionBinding
import org.koin.android.viewmodel.ext.android.viewModel


class SuggestionFragment : DialogFragment() {
    private lateinit var binding: FragmentSuggestionBinding
    private val viewModel: SuggestionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuggestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()

    }

    private fun setupListeners() {
        binding.btnSendSuggestion.setOnClickListener {
            val suggestionText = binding.edtSuggestion.text.toString()
            val suggestion = SuggestionResponse(
                text = suggestionText
            )
            viewModel.addSuggestionMusic(suggestion)
        }
    }
}

