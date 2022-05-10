package com.example.freeentproject.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.GridListPeliBinding
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de peliculas existentes en la bd. En el metodo
onCreateViewHolder le pasamos el layaout de corresponde con las lista de items. En el método
getItemCount le pasamos el numero de  items que tiene que pintar. En el método onBindViewHolder el
ViewHolder nos permite blindear cada elemento utilizando su posicion en la lista. En la inner class
blindeamos y cargamos la imagen a través de un método propio suporteado por la librería Picasso para
cargar imágenes. Aquí también utilizamos una interface Callback que nos permite saber en que item exactamente
hemos tocado, para así poder devolver su contenido.
 */

class GridAdapterPeli (private var todasPelis: List<ModeloPeli>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var callback: PelisClickCallback

    interface PelisClickCallback {
        fun clickPeli (pelicula: ModeloPeli)
    }

    fun setCallback (call: PelisClickCallback) {
        callback = call
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_peli, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val tvs = todasPelis[position]
                holder.bind(tvs)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = GridListPeliBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                val tituloPeli = todasPelis[absoluteAdapterPosition]
                callback.clickPeli(tituloPeli)
            }
        }

        fun bind(cine: ModeloPeli) {
            Utils.loadImage((cine.imagen ?: "" ), binding.imgGridPeli)
        }
    }

    override fun getItemCount(): Int {
        return todasPelis.size
    }
}