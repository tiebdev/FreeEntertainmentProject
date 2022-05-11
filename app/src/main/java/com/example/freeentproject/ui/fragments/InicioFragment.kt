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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freeentproject.databinding.FragmentInicioBinding
import com.example.freeentproject.domain.models.ModeloRadio
import com.example.freeentproject.ui.adapters.AdapterPadre
import com.example.freeentproject.ui.adapters.AdapterRadio
import com.example.freeentproject.ui.adapters.GridAdapterRadio
import com.example.freeentproject.ui.view_model.InicioViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment que mostrara la lista padre que contiene el resto de listas correspondientes con los
diferentes tipos de listas. Creamos el objeto viewModels y el objeto GridAdapterTv. Inicializamos e
instanciamos el adaptador padre antes de recibir cualquier dato. Después tenemos un metodo
observer() que ejecutamos en el metodo onViewCreated(). A través de la Corrutina conseguimos obtener
cuatro "observadores" que nos devuelve cuatro listas correspondientes con los modelos de Pelis,
Radio, Tv, Santander. Aquí y ahora es posible ejecutar la funcion addXXX pasandole las cuatro listas
de objetos y por último notificamos al adaptador los cambios para que muestre la lista.
 */

@AndroidEntryPoint
class InicioFragment: Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    private val inicioViewModel: InicioViewModel by viewModels()
    private lateinit var adapter: AdapterPadre
    private lateinit var adapterRadio: AdapterRadio

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterPadre()
        observer()
        binding.recyclerPadre.layoutManager = LinearLayoutManager(context)
        binding.recyclerPadre.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun observer() {
        inicioViewModel.pelis.observe(viewLifecycleOwner, Observer {
            adapter.addPelis(it)
            adapter.notifyDataSetChanged()
        })

        inicioViewModel.tvs.observe(viewLifecycleOwner, Observer {
            adapter.addTv(it)
            adapter.notifyDataSetChanged()
        })

        inicioViewModel.radios.observe(viewLifecycleOwner, Observer {
            adapter.addRadio(it)
            adapter.notifyDataSetChanged()
        })

        inicioViewModel.santander.observe(viewLifecycleOwner, Observer {
            adapter.addSantander(it)
            adapter.notifyDataSetChanged()
        })
    }
}