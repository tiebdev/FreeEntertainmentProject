package com.example.freeentproject.ui.activitys
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.freeentproject.databinding.ActivityExoPlayerPlayRadioBinding
import com.example.freeentproject.utils.Utils
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class ExoPlayerPlayRadio : AppCompatActivity() {
    private lateinit var binding: ActivityExoPlayerPlayRadioBinding
    private lateinit var player: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoPlayerPlayRadioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addComponents()
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

    private fun releasePlayer() {
        player.stop()
        player.release()
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

    private fun addComponents() {
        Utils.loadImage((intent.getStringExtra("imagen") ?: " "), binding.imgPlayRadio)
    }
}