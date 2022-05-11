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

/*
Fragment en el que mostramos las cadenas de tv disponibles en la bd. Creamos el objeto viewModels y
el objeto GridAdapterTv. Después tenemos un metodo observer() que ejecutamos en el metodo
onViewCreated(). A través de la Corrutina conseguimos obtener un "observador" que nos devuelve una
lista tipo ModeloTv. Aquí y ahora es posible instanciar el adaptador pasandole esa lista,
personalizar el adaptador y por último notificamos al adaptador los cambios para que muestre la
lista.
 */

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