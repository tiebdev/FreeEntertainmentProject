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
import com.example.freeentproject.databinding.FragmentGridFutbolBinding
import com.example.freeentproject.domain.models.ModeloLiga
import com.example.freeentproject.ui.adapters.GridAdapterLigas
import com.example.freeentproject.ui.view_model.FutbolFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment en el que mostramos las dos ligas disponibles en la bd. Añadimos la interface Callback.
Blindeamos la vista a través del viewBinding. Creamos el objeto viewModels y el objeto
GridAdapterLigas. Después tenemos un metodo observer() que ejecutamos en el metodo
onViewCreated(). A través de la Corrutina conseguimos obtener un "observador" que nos devuelve
una lista tipo ModeloLiga. Aquí y ahora es posible instanciar el adaptador pasandole esa lista,
personalizar el adaptador, llamar al metodo de la interface Callback y por último notificamos
al adaptador los cambios para que muestre la lista.
 */

@AndroidEntryPoint
class FutbolFragment: Fragment(), GridAdapterLigas.LigasClickCallback {

    private var _binding: FragmentGridFutbolBinding? = null
    private val binding get() = _binding!!
    private val ligaViewModel : FutbolFragmentViewModel by viewModels()
    private lateinit var adapterLigas : GridAdapterLigas

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridFutbolBinding.inflate(inflater, container, false)
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
        ligaViewModel.ligas.observe(viewLifecycleOwner, Observer {
            adapterLigas = GridAdapterLigas(it)
            binding.gridLiga.layoutManager = GridLayoutManager(context, 3)
            binding.gridLiga.adapter = adapterLigas
            adapterLigas.setCallback(this)
            adapterLigas.notifyDataSetChanged()
        })
    }

    override fun clickLiga(liga: ModeloLiga) {

        if (liga.nombre == "santander") {
            val direccionSantander = FutbolFragmentDirections.actionFutbolFragmentToSantanderFragment(liga)
            findNavController().navigate(direccionSantander)
        }else {
            val direccionSmartbank = FutbolFragmentDirections.actionFutbolFragmentToSmartbankFragment(liga)
            findNavController().navigate(direccionSmartbank)
        }
    }
}