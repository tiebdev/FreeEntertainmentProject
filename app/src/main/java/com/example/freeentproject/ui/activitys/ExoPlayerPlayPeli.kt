package com.example.freeentproject.ui.activitys
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.freeentproject.databinding.ActivityExoPlayerPlayPeliBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint

/*
En esta clase se ejecutará la película elegida por el usuario en el GridFragment de las películas.
Tenemos un objeto tipo ExoPlayer que se encargará se ejecutar los métodos correspondientes a la
reproducción del medios seleccionado. A través de los métodos override como son onStart(), onPause(),
onStop() y onResume(). A parte creamos el método item() para convertir la url recibida a través de
un intent del fragment anterior (PrePeliFragment) en un MediaItem que metemos en el método
reproducir() para que el objeto ExoPlayer reproduzca la película. El método hideSystemUi() aunque
está deprecado, y convendría cambiarlo no dentro de mucho tiempo, nos permite mantener la pantalla
completa, la invisibilidad de la barra de control al pasar unos segundos, etc..
 */

@AndroidEntryPoint
class ExoPlayerPlayPeli : AppCompatActivity() {

    private lateinit var binding: ActivityExoPlayerPlayPeliBinding
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoPlayerPlayPeliBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        finish()
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
        player = ExoPlayer.Builder(applicationContext).build()
        binding.videoView.player = player

        val url = intent.getStringExtra("url")

        val mediaItem = item(url!!)
        player.addMediaItem(mediaItem)
        player.prepare()
        player.play()
        player.isCurrentMediaItemLive
    }

    private fun item(url: String): MediaItem {
        return MediaItem.fromUri(Uri.parse(url))
    }
}