package com.santos.jukebox.establishment.ui.fragment

import androidx.fragment.app.Fragment
import com.santos.jukebox.establishment.viewmodel.ManagerMusicViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MusicsManagerFragment : Fragment() {
    private lateinit var binding: FragmentRegisterMusicBinding
    private val viewModelRegister:
            ManagerMusicViewModel by viewModel()

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

        binding.recycleTypesMusic.adapter = adapter
        setupListeners()
        setupObservables()
    }

    private fun setupListeners() {
        adapter.onChecked = {
            viewModelRegister.setTypeMusics(it)
        }
}