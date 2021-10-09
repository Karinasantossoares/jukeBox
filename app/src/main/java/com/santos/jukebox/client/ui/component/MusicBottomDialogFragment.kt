package com.example.register_android.ui.component

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.santos.jukebox.R
import com.santos.jukebox.client.data.Music
import com.santos.jukebox.client.viewmodel.ClientViewModel
import com.santos.jukebox.databinding.FragmentBottomDialogMusicBinding
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MusicBottomDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentBottomDialogMusicBinding
    private val viewModel: ClientViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomDialogMusicBinding.inflate(inflater, container, true)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }


    private fun setupListeners() {
        binding.root.setBackgroundColor(Color.TRANSPARENT)

        val put = arguments?.getParcelable<Music>(MUSIC)
        binding.NameMusic.text = put?.title
        binding.SubtitleAuthor.text = put?.author
        binding.btnAddQueue.setOnClickListener {
            put?.let {
                viewModel.addMusicQueue(true, put)
            }
            findNavController().popBackStack()
        }
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val MUSIC = "music"
    }
}