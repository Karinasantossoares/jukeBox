package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.santos.establishment.viewmodel.RegisterEstablishmentViewModel
import com.santos.jukebox.databinding.FragmentRegisterMusicBinding
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegisterMusicFragment : Fragment() {
    private lateinit var binding: FragmentRegisterMusicBinding
    private val viewModelRegister:
            RegisterEstablishmentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            setupListeners()
            setupObservables()
        }

    private fun setupListeners() {
        binding.btnRegister.setOnClickListener {
            val nameMusic = binding.etName.text.toString()
            val author = binding.etAuthor.text.toString()
            val description = binding.etDescription.text.toString()
            val music = RegisterMusicEstablishment(
                title = nameMusic,
                author = author,
                description = description
            )
            viewModelRegister.saveNewMusic(music)
        }

    }

    private fun setupObservables() {
        viewModelRegister.loadResult.observe(viewLifecycleOwner, Observer {
            binding.pbLoadRegister.isVisible = it
        })

        viewModelRegister.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModelRegister.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }


}