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
import com.example.freeentproject.databinding.FragmentGridRadioBinding
import com.example.freeentproject.domain.models.ModeloRadio
import com.example.freeentproject.ui.adapters.AdapterRadio
import com.example.freeentproject.ui.adapters.GridAdapterRadio
import com.example.freeentproject.ui.view_model.RadioFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment en el que mostramos las radios disponibles en la bd. Añadimos la interface Callback.
Blindeamos la vista a través del viewBinding. Creamos el objeto viewModels y el objeto
GridAdapterRadio. Después tenemos un metodo observer() que ejecutamos en el metodo
onViewCreated(). A través de la Corrutina conseguimos obtener un "observador" que nos devuelve
una lista tipo ModeloRadio. Aquí y ahora es posible instanciar el adaptador pasandole esa lista,
personalizar el adaptador, llamar al método de la interface Callback y por último notificamos
al adaptador los cambios para que muestre la lista. El método onClick() nos hace viajar a través del
findNavController() al fragment donde se reproducirá la emisora de radio.
 */

@AndroidEntryPoint
class RadioFragment: Fragment(), GridAdapterRadio.RadiosClickCallback {

    private var _binding: FragmentGridRadioBinding? = null
    private val binding get() = _binding!!
    private val radioViewModel : RadioFragmentViewModel by viewModels()
    private lateinit var adapterRadio : GridAdapterRadio

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGridRadioBinding.inflate(inflater , container, false)
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
        radioViewModel.radios.observe(viewLifecycleOwner, Observer {
            adapterRadio = GridAdapterRadio(it)
            binding.gridRadio.layoutManager = GridLayoutManager(context, 3)
            binding.gridRadio.adapter = adapterRadio
            adapterRadio.setCallback(this)
            adapterRadio.notifyDataSetChanged()
        })
    }

    override fun clickRadio(radio: ModeloRadio) {
        val direccion = RadioFragmentDirections.actionRadioFragmentToPlayRadioFragment(radio)
        findNavController().navigate(direccion)
    }
}