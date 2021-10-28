package com.santos.jukebox.establishment.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.santos.jukebox.R
import com.santos.jukebox.client.data.SuggestionResponse
import com.santos.jukebox.databinding.FragmentRegisterMusicBinding
import com.santos.jukebox.establishment.data.RegisterMusicEstablishment
import com.santos.jukebox.establishment.ui.action.EventRegisterMusic
import com.santos.jukebox.establishment.ui.adapter.SelectTypeMusicAdapter
import com.santos.jukebox.establishment.viewmodel.RegisterMusicViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterMusicFragment : Fragment() {
    private lateinit var binding: FragmentRegisterMusicBinding
    private val viewModelRegister:
            RegisterMusicViewModel by viewModel()

    private val adapter by lazy { SelectTypeMusicAdapter() }
    private val builder by lazy { AlertDialog.Builder(requireContext()) }
    private val dialog by lazy { builder.create() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterMusicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleTypesMusic.adapter = adapter
        arguments?.getParcelable<RegisterMusicEstablishment>(EXTRA_KEY_MUSIC)?.let {
            viewModelRegister.setEditMusic(it)
        }
        arguments?.getParcelable<SuggestionResponse>(EXTRA_KEY_NAME_MUSIC)?.let {
            viewModelRegister.setRegisterRecommnededMusic(it)
        }
        setupListeners()
        setupObservables()
    }

    private fun setupListeners() {
        with(binding) {
            adapter.onChecked = {
                viewModelRegister.setTypeMusics(it)
            }

            adapter.onPressedClick = {
                viewModelRegister.setOnLongClick(it)
            }

            btnAddType.setOnClickListener {
                findNavController().navigate(R.id.to_type_music)
            }

            builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModelRegister.deleteTypeMusic()
                dialog.dismiss()
            }

            btnRegister.setOnClickListener {
                val nameMusic = binding.etName.text.toString()
                val author = binding.etAuthor.text.toString()
                viewModelRegister.saveOrEditMusic(
                    title = nameMusic,
                    author = author,
                    types = adapter.musicsChecked,
                    isVisible = binding.checkBox.isChecked

                )
            }

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
                it.isEditionMusic -> {
                    binding.checkBox.isChecked = it.newMusic.visible
                    binding.etName.setText(it.newMusic.title)
                    binding.etAuthor.setText(it.newMusic.author)
                    adapter.updateList(it.allTypeMusics, it.newMusic.types)
                }
                it.isRegistrationRecommendedMusic -> {
                    binding.checkBox.isChecked = it.newMusic.visible
                    binding.etName.setText(it.newMusic.title)
                }
            }
        })

        viewModelRegister.actionLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is EventRegisterMusic.ShowMessage -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                EventRegisterMusic.Success -> {
                    findNavController().popBackStack()
                }
                EventRegisterMusic.HideDialog -> {
                    dialog.dismiss()
                }
                is EventRegisterMusic.ShowDialogDialog -> {
                    dialog.setTitle(it.title)
                    dialog.setMessage(it.message)
                    dialog.show()
                }
            }

        })
    }

    companion object {
        const val EXTRA_KEY_MUSIC = "EXTRA_KEY_MUSIC"
        const val EXTRA_KEY_NAME_MUSIC = "EXTRA_KEY_NAME_MUSIC"
    }

}