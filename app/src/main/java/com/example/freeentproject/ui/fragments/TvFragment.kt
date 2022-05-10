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
import com.example.freeentproject.databinding.FragmentGridTvBinding
import com.example.freeentproject.ui.adapters.GridAdapterTv
import com.example.freeentproject.ui.view_model.TvFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : Fragment() {

    private var _binding: FragmentGridTvBinding? = null
    private val binding get() =  _binding!!
    private val tvViewModel : TvFragmentViewModel by viewModels()
    private lateinit var adapterTv: GridAdapterTv

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridTvBinding.inflate(inflater, container, false)
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
        tvViewModel.tvs.observe(viewLifecycleOwner, Observer {
            adapterTv = GridAdapterTv(it)
            binding.gridTv.layoutManager = GridLayoutManager(context, 3)
            binding.gridTv.adapter = adapterTv
            adapterTv.notifyDataSetChanged()
        })
    }
}