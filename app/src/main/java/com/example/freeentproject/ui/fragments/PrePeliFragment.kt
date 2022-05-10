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