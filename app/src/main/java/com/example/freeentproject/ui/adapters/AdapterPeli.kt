package com.example.freeentproject.ui.adapters
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.ListPeliBinding
import com.example.freeentproject.domain.models.ModeloHijoPeli
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.ui.activitys.ExoPlayerPlayPeli
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de peliculas. En el metodo onCreateViewHolder le pasamos
el layaout de corresponde con las lista de items. En el método getItemCount le pasamos el numero de
items que tiene que pintar. En el método onBindViewHolder el ViewHolder nos permite blindear cada
elemento utilizando su posicion en la lista. En la inner class blindeamos y cargamos la imagen a
través de un método propio suporteado por la librería Picasso para cargar imágenes.
 */

class AdapterPeli(var items: ModeloHijoPeli): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.list_peli, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return items.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val pelis = items.list[position]
                holder.bind(pelis)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListPeliBinding.bind(itemView)

        fun bind(peli: ModeloPeli) {

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ExoPlayerPlayPeli::class.java)
                intent.putExtra("url", peli.url!!)
                context.startActivity(intent)
            }

            Utils.loadImage(peli.imagen ?: " ", binding.imgPeli)
        }
    }
}