package com.example.freeentproject.ui.activitys
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.freeentproject.databinding.ActivityExoPlayerPlayResumenBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint

/*
En esta clase se ejecutará el resumen del partido elegido por el usuario en el GridFragment de los
partidos de cualquiera de las dos ligas. Tenemos un objeto tipo ExoPlayer que se encargará se
ejecutar los métodos correspondientes a la reproducción del medios seleccionado. A través de los
métodos override como son onStart(), onPause(), onStop() y onResume(). A parte creamos el método
item() para convertir la url recibida a través de un intent de los fragment anteriores
(SantanderFragment y SmartbankFragment) en un MediaItem que metemos en el método reproducir() para
que el objeto ExoPlayer reproduzca el partido. El método hideSystemUi() aunque está deprecado, y
convendría cambiarlo no dentro de mucho tiempo, nos permite mantener la pantalla completa,
la invisibilidad de la barra de control al pasar unos segundos, etc..
 */

@AndroidEntryPoint
class ExoPlayerPlayResumen : AppCompatActivity(), Player.Listener {

    private lateinit var binding: ActivityExoPlayerPlayResumenBinding
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoPlayerPlayResumenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        back()
    }
    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        savePos()
    }

    override fun onStop() {
        super.onStop()
        savePos()
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        continuePlayer(savePos())
    }

    override fun onDestroy() {
        super.onDestroy()
        savePos()
    }

    private fun savePos(): Long {
        if (player != null){
            playbackPosition = player!!.currentPosition
            player!!.removeListener(this)
            player!!.release()
            player = null
        }
        return playbackPosition
    }

    private fun continuePlayer(playPosition: Long) {
        player = ExoPlayer.Builder(applicationContext).build()
        player!!.addListener(this)
        binding.videoView.player = player

        val url = intent.getStringExtra("url")
        val mediaItem = item(url!!)

        player!!.setMediaItem(mediaItem)
        player!!.isCurrentMediaItemLive
        playWhenReady
        player!!.seekTo(currentItem, playPosition)
        player!!.prepare()
        player!!.play()
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(applicationContext).build()
        player!!.addListener(this)
        binding.videoView.player = player

        val url = intent.getStringExtra("url")
        val mediaItem = item(url!!)

        player!!.setMediaItem(mediaItem)
        player!!.isCurrentMediaItemLive
        playWhenReady
        player!!.seekTo(currentItem, playbackPosition)
        player!!.prepare()
        player!!.play()
    }

    private fun hideSystemUi() {
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun item(url: String): MediaItem {
        return MediaItem.fromUri(Uri.parse(url))
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)

        when(playbackState) {

            Player.STATE_BUFFERING -> {
                binding.isLoading.visibility = View.VISIBLE
            }

            Player.STATE_READY -> {
                binding.isLoading.visibility = View.INVISIBLE
            }
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(this@ExoPlayerPlayResumen, "${error.message}", Toast.LENGTH_SHORT)
    }

    private fun back() {
        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        super.onIsPlayingChanged(isPlaying)
        if (!isPlaying) {
            binding.back.visibility = View.VISIBLE
        }
    }
}