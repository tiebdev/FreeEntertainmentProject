package com.example.freeentproject.ui.fragments
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.freeentproject.R
import com.example.freeentproject.databinding.FragmentPrePeliBinding
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.ui.activitys.ExoPlayerPlayPeli
import com.example.freeentproject.utils.Utils
import com.google.firebase.firestore.FirebaseFirestore
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
        binding.duracionPeli.text = pelicula.duracion
        binding.lenguajePeli.text = pelicula.lenguaje
        Utils.loadImage(pelicula.imagen ?: " ", binding.imgPrePeli)

        binding.playButton.setOnClickListener() {
            val intent = Intent(context, ExoPlayerPlayPeli::class.java)
            intent.putExtra("url", pelicula.url!!)
            startActivity(intent)
        }

        binding.fav.setOnClickListener {
            binding.fav.setIconTintResource(R.color.ALBARICOQUE)
            val db = FirebaseFirestore.getInstance()
            val data = hashMapOf(
                "imagen" to pelicula.imagen,
                "url" to pelicula.url,
                "titulo" to pelicula.titulo,
                "descripcion" to pelicula.descripcion,
                "duracion" to pelicula.duracion,
                "lenguaje" to pelicula.lenguaje
            )

            db.collection("pelisFav")
                .document(pelicula.titulo!!)
                .set(data)
                .addOnSuccessListener { docu ->
                    Log.d(ContentValues.TAG, "added")
                }
                .addOnFailureListener {
                    Log.d(ContentValues.TAG, "error")
                }
        }
    }
}