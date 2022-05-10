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
import com.example.freeentproject.databinding.FragmentGridSmartbankBinding
import com.example.freeentproject.ui.adapters.GridAdapterSmartbankFragment
import com.example.freeentproject.ui.view_model.SmartbankFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SmartbankFragment: Fragment() {

    private var _binding: FragmentGridSmartbankBinding? = null
    private val binding get() = _binding!!
    private val smartbankViewModel: SmartbankFragmentViewModel by viewModels()
    private lateinit var adapterSmartbank : GridAdapterSmartbankFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridSmartbankBinding.inflate(inflater, container, false)
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
        smartbankViewModel.smartbank.observe(viewLifecycleOwner, Observer {
            adapterSmartbank = GridAdapterSmartbankFragment(it)
            binding.gridSmartbank.layoutManager = GridLayoutManager(context, 3)
            binding.gridSmartbank.adapter = adapterSmartbank
            adapterSmartbank.notifyDataSetChanged()
        })
    }
}