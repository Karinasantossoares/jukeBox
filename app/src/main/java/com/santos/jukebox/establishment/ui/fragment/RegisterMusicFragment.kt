package com.santos.jukebox.establishment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.santos.jukebox.client.viewmodel.ClientViewModel
import com.santos.jukebox.establishment.viewmodel.RegisterMusicViewModel
import com.santos.jukebox.databinding.FragmentRegisterMusicBinding
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.adapter.TypeMusicAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterMusicFragment : Fragment() {
    private lateinit var binding: FragmentRegisterMusicBinding
    private val viewModelRegister:
            RegisterMusicViewModel by viewModel()

    private val adapter by lazy { TypeMusicAdapter() }

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
        adapter.onChecked = {
            viewModelRegister.setTypeMusics(it)
        }
        binding.recycleTypesMusic.adapter = adapter

        binding.btnRegister.setOnClickListener {
            val nameMusic = binding.etName.text.toString()
            val author = binding.etAuthor.text.toString()
            val music = RegisterMusicEstablishment(
                title = nameMusic,
                author = author,
                types = adapter.musicsChecked
            )
            viewModelRegister.saveNewMusic(music)
        }
    }

    private fun setupObservables() {
        viewModelRegister.stateLiveData.observe(viewLifecycleOwner, {
            binding.pbLoadRegister.isVisible = it.isLoadingSaveMusic
            binding.btnRegister.isVisible = !it.isLoadingSaveMusic
            binding.pbLoadTypes.isVisible = it.isLoadingGetTypeMusics
            when {
                it.allTypeMusics.isNotEmpty() -> {
                    adapter.updateList(it.allTypeMusics, it.newMusic.types)
                }
            }

        })

        viewModelRegister.actionLiveData.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        })
    }


}