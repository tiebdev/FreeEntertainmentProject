package com.example.freeentproject.ui.fragments
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.freeentproject.databinding.FragmentPlayRadioBinding
import com.example.freeentproject.domain.models.ModeloRadio
import com.example.freeentproject.utils.Utils
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint

/*
Fragment en el que reproducimos la radio seleccionada de la bd. Blindeamos la vista a través del
viewBinding. Creamos el objeto viewModels y el objeto navArgs() para pasarle el objeto ModeloRadio
a través del nav_grav (layout). A través de los métodos override como son onStart(), onPause(),
onStop() y onResume(). A parte creamos el método item() para convertir la url recibida a través de
un intent del fragment anterior (RadioFragment) en un MediaItem que metemos en el método
reproducir() para que el objeto ExoPlayer reproduzca la película. El método releasePlayer() nos
permite que la música se corte cuando navegamos hacia otro fragment de la aplicación.
 */

@AndroidEntryPoint
class PlayRadioFragment: Fragment() {

    private var _binding: FragmentPlayRadioBinding? = null
    private val binding get() = _binding!!
    private lateinit var radio : ModeloRadio
    private lateinit var player: ExoPlayer
    private val args: PlayRadioFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayRadioBinding.inflate(inflater, container, false)
        radio = args.radio
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Utils.loadImage(radio.imagen ?: " ", binding.imgPlayRadio)
    }

    override fun onStart() {
        super.onStart()
        reproducir()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
    }

    private fun releasePlayer() {
        player.stop()
        player.release()
    }

    private fun hideSystemUi() {
        binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun reproducir() {
        player = ExoPlayer.Builder(requireContext()).build()
        binding.videoView.player = player

        val mediaItem = item(radio.url!!)
        player.addMediaItem(mediaItem)
        player.prepare()
        player.play()
        player.isCurrentMediaItemLive
    }

    private fun item(url: String): MediaItem {
        return MediaItem.fromUri(Uri.parse(url))
    }

}