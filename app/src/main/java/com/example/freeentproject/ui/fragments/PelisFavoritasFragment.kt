package com.example.freeentproject.ui.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.freeentproject.R
import com.example.freeentproject.databinding.FragmentPelisFavoritasBinding
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.ui.adapters.AdapterPelisFav
import com.example.freeentproject.ui.view_model.PelisFavViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PelisFavoritasFragment: Fragment(), AdapterPelisFav.PelisClickCallback {

    private var _binding: FragmentPelisFavoritasBinding? = null
    private val binding get() = _binding!!
    private val pelisFavViewModel : PelisFavViewModel by viewModels()
    private lateinit var adapterPelisFav: AdapterPelisFav

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPelisFavoritasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        showAlertDialog()
        back()
    }

    private fun observer() {
        pelisFavViewModel.pelisFav.observe(viewLifecycleOwner, Observer { it ->
            adapterPelisFav = AdapterPelisFav(it)
            binding.recyclerPelisFav.layoutManager = GridLayoutManager(context, 3)
            binding.recyclerPelisFav.adapter = adapterPelisFav
            adapterPelisFav.setCallback(this)
            adapterPelisFav.notifyDataSetChanged()
        })
    }

    override fun clickPeliFav(pelicula: ModeloPeli) {
        val direccion = PelisFavoritasFragmentDirections.actionPelisFavoritasFragmentToPrePeliFragment(pelicula)
        findNavController().navigate(direccion)
    }

    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.tituloDialog))
            .setMessage(resources.getString(R.string.mensajeDialog))
            .setPositiveButton(resources.getString(R.string.vale)) { dialog, _ ->
                dialog.dismiss() }
            .show()
    }

    private fun back(){
        binding.back.setOnClickListener {
            val direcccion = PelisFavoritasFragmentDirections.actionPelisFavoritasFragmentToPeliFragment()
            findNavController().navigate(direcccion)
        }
    }
}