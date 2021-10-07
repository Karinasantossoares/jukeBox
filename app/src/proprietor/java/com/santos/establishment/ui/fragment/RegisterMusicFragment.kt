package com.santos.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.santos.establishment.data.Music
import com.santos.establishment.viewmodel.EstablishmentViewModel
import com.santos.jukebox.databinding.FragmentRegisterMusicBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegisterMusicFragment : Fragment() {
    private lateinit var binding: FragmentRegisterMusicBinding
    private val viewModel: EstablishmentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupListeners()
        setupObservables()

    }

    private fun setupListeners() {
        val nameMusic = binding.etName.text.toString()
        val author = binding.etAuthor.text.toString()
        val description = binding.etDescription.text.toString()
        val music = Music(title = nameMusic, author = author, description = description)
        viewModel.saveNewMusic(music)
    }

    private fun setupObservables() {
        viewModel.loadResult.observe(viewLifecycleOwner, Observer {
            binding.pbLoadRegister.isVisible = it
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }


}