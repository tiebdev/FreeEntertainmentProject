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
import com.example.freeentproject.databinding.FragmentGridSmartbankBinding
import com.example.freeentproject.ui.adapters.GridAdapterSmartbankFragment
import com.example.freeentproject.ui.view_model.SmartbankFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment en el que mostramos los resumenes de los partidos de la Liga Smartbank disponibles en la bd.
Creamos el objeto viewModels y el objeto GridAdapterSmartbank. Después tenemos un metodo observer()
que ejecutamos en el metodo onViewCreated(). A través de la Corrutina conseguimos obtener un
"observador" que nos devuelve una lista tipo ModeloSmartbank. Aquí y ahora es posible instanciar el
adaptador pasandole esa lista, personalizar el adaptador y por último notificamos
al adaptador los cambios para que muestre la lista.
 */

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
        back()
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

    private fun back(){
        binding.back.setOnClickListener {
            val direcccion = SmartbankFragmentDirections.actionSmartbankFragmentToFutbolFragment()
            findNavController().navigate(direcccion)
        }
    }
}