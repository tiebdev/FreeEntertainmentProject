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
import com.example.freeentproject.databinding.FragmentGridSantanderBinding
import com.example.freeentproject.ui.adapters.GridAdapterSantanderFragment
import com.example.freeentproject.ui.view_model.SantanderFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment en el que mostramos los resumenes de los partidos de la Liga Santander disponibles en la bd.
Creamos el objeto viewModels y el objeto GridAdapterSantander. Después tenemos un metodo observer()
que ejecutamos en el metodo onViewCreated(). A través de la Corrutina conseguimos obtener un
"observador" que nos devuelve una lista tipo ModeloSantander. Aquí y ahora es posible instanciar el
adaptador pasandole esa lista, personalizar el adaptador y por último notificamos
al adaptador los cambios para que muestre la lista.
 */

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
        back()
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

    private fun back(){
        binding.back.setOnClickListener {
            val direcccion = SantanderFragmentDirections.actionSantanderFragmentToFutbolFragment()
            findNavController().navigate(direcccion)
        }
    }
}