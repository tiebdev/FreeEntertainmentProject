package com.example.freeentproject.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.freeentproject.databinding.FragmentGridSantanderBinding
import com.example.freeentproject.ui.adapters.GridAdapterSantanderFragment
import com.example.freeentproject.ui.view_model.SantanderFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SantanderFragment : Fragment() {

    private var _binding: FragmentGridSantanderBinding? = null
    private val binding get() = _binding!!
    private val santanderViewModel: SantanderFragmentViewModel by viewModels()
    private lateinit var adapterSantander : GridAdapterSantanderFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridSantanderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observer() {
        santanderViewModel.santander.observe(viewLifecycleOwner, Observer {
            adapterSantander = GridAdapterSantanderFragment(it)
            binding.gridSantander.layoutManager = GridLayoutManager(context, 3)
            binding.gridSantander.adapter = adapterSantander
            adapterSantander.notifyDataSetChanged()
        })
    }
}