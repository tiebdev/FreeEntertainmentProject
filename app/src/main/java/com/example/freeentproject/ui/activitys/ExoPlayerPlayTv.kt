package com.example.freeentproject.ui.activitys
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.freeentproject.databinding.ActivityExoPlayerPlayTvBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.hilt.android.AndroidEntryPoint

/*
En esta clase se ejecutará la cadena de tv elegida por el usuario en el GridFragment de los
tv. Tenemos un objeto tipo ExoPlayer que se encargará se ejecutar los métodos correspondientes a la
reproducción del medios seleccionado. A través de los métodos override como son onStart(), onPause(),
onStop() y onResume(). A parte creamos una enum class URLType() para darle la propiedad HLS a la url
recibida a través de un intent del fragment anterior (TvFragment) que metemos en el método
reproducir() donde se realiza la conversion de la tv del tipo m3u8 (que es el formato en el que
se recibe la cadena) mediante el dataSourceFactory y HlsMediaSource para que el objeto ExoPlayer
reproduzca la cadena de tv. El método hideSystemUi() aunque está deprecado, y convendría cambiarlo
no dentro de mucho tiempo, nos permite mantener la pantalla completa, la invisibilidad de la barra
de control al pasar unos segundos, etc..
 */

@AndroidEntryPoint
class ExoPlayerPlayTv : AppCompatActivity() {

    private lateinit var binding: ActivityExoPlayerPlayTvBinding
    private lateinit var media: MediaSource
    private lateinit var urlType: URLType
    private  lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoPlayerPlayTvBinding.inflate(layoutInflater)
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
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    private fun reproducir() {
        player = ExoPlayer.Builder(applicationContext).build()
        binding.videoView.player = player

        val url = intent.getStringExtra("url")

        urlType = URLType.HLS
        urlType.url = url!!
        player.seekTo(0)

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this, applicationInfo.name)
        )

        media = HlsMediaSource.Factory(dataSourceFactory).createMediaSource(
            MediaItem.fromUri(Uri.parse(urlType.url))
        )

        player.setMediaSource(media)
        player.prepare()
        player.play()
    }

    enum class URLType(var url: String) {
        HLS("")
    }
}