package com.example.freeentproject.ui.fragments
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.freeentproject.databinding.FragmentPrePeliBinding
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.ui.activitys.ExoPlayerPlayPeli
import com.example.freeentproject.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment previo a reproducir la pelicula seleccionada de la bd. Blindeamos la vista a través del
viewBinding. Creamos el objeto viewModels y el objeto navArgs() para pasarle el objeto ModeloPeli
a través del nav_grav (layout). Ya en la vista mostramos en dos TextView el titulo y la descripción,
un ImageView donde mostraremos el cartel mediante la función Utils que usa la librería Picasso y
añadimos finalmente un Button para ver el video. Con ese Button viajaremos a la activity que
reproduce la película. En ese viaje mediante un Intent, enviamos también la url donde se aloja el
archivo de la película.
*/

@AndroidEntryPoint
class PrePeliFragment : Fragment() {

    private var _binding: FragmentPrePeliBinding? = null
    private val binding get() = _binding!!
    private lateinit var pelicula : ModeloPeli
    private val args: PrePeliFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrePeliBinding.inflate(inflater, container, false)
        pelicula = args.peli
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewTitulo.text = pelicula.titulo
        binding.textViewDescripcion.text = pelicula.descripcion
        Utils.loadImage(pelicula.imagen ?: " " , binding.imgPrePeli)

        binding.playButton.setOnClickListener() {
            val intent = Intent(context, ExoPlayerPlayPeli::class.java)
            intent.putExtra("url", pelicula.url!!)
            startActivity(intent)
        }
    }
}