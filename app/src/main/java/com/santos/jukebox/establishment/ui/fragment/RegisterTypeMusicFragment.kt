package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.santos.jukebox.databinding.FragmentRegisterTypeMusicBinding
import com.santos.jukebox.establishment.ui.state.StateTypeMusic
import com.santos.jukebox.establishment.viewmodel.RegisterTypeMusicViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterTypeMusicFragment : Fragment() {
    private lateinit var binding: FragmentRegisterTypeMusicBinding
    private val viewModelRegister:
            RegisterTypeMusicViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterTypeMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservables()
    }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            viewModelRegister.saveNewTypeMusic(binding.edtType.text.toString())
        }
    }

    private fun setupObservables() {
        viewModelRegister.liveData.observe(viewLifecycleOwner, {
            when (it) {
                is StateTypeMusic.Success -> {
                    binding.pbLoadRegister.isVisible = false
                    findNavController().popBackStack()
                }
                StateTypeMusic.Loading -> {
                    binding.pbLoadRegister.isVisible = true
                }
                is StateTypeMusic.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}