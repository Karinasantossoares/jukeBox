package com.santos.jukebox.establishment.ui.fragment

import androidx.fragment.app.Fragment


//class MusicsFragment : Fragment() {
//    private lateinit var binding: FragmentRegisterMusicBinding
//    private val viewModelRegister:
//            RegisterMusicViewModel by sharedViewModel()
//
//    private val adapter by lazy { TypeMusicAdapter() }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentRegisterMusicBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.recycleTypesMusic.adapter = adapter
//        setupListeners()
//        setupObservables()
//    }
//
//    private fun setupListeners() {
//        adapter.onChecked = {
//            viewModelRegister.setTypeMusics(it)
//        }
//}