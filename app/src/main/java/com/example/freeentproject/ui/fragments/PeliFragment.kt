package com.example.freeentproject.ui.fragments
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.freeentproject.databinding.FragmentGridPeliBinding
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.ui.adapters.GridAdapterPeli
import com.example.freeentproject.ui.view_model.PeliFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment en el que mostramos las peliculas disponibles en la bd. Añadimos la interface Callback.
Blindeamos la vista a través del viewBinding. Creamos el objeto viewModels y el objeto
GridAdapterPeli. Implementamos métodos override para crear, destruir y mostrar las vistas, además
de filtrar el item seleccionado. Después tenemos un metodo observer() que ejecutamos en el metodo
onViewCreated(). A través de la Corrutina conseguimos obtener un "observador" que nos devuelve
una lista tipo ModeloPeli. Aquí y ahora es posible instanciar el adaptador pasandole esa lista,
personalizar el adaptador, llamar al metodo de la interface Callback y por último notificamos
al adaptador los cambios para que muestre la lista.
 */

@AndroidEntryPoint
class PeliFragment: Fragment(), GridAdapterPeli.PelisClickCallback {

    private var _binding: FragmentGridPeliBinding? = null
    private val binding get() = _binding!!
    private val peliViewModel : PeliFragmentViewModel by viewModels()
    private lateinit var adapterPeli : GridAdapterPeli

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridPeliBinding.inflate(inflater, container, false)
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
        peliViewModel.pelis.observe(viewLifecycleOwner, Observer {
            adapterPeli = GridAdapterPeli(it)
            binding.gridPeli.layoutManager = GridLayoutManager(context, 3)
            binding.gridPeli.adapter = adapterPeli
            adapterPeli.setCallback(this)
            adapterPeli.notifyDataSetChanged()
        })
    }

    override fun clickPeli(pelicula: ModeloPeli) {
        val direccion = PeliFragmentDirections.actionPeliFragmentToPrePeliFragment(pelicula)
        findNavController().navigate(direccion)
    }
}