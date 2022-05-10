package com.example.freeentproject.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.GridListFutbolBinding
import com.example.freeentproject.domain.models.ModeloLiga
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de ligas existentes en la bd. En el metodo
onCreateViewHolder le pasamos el layaout de corresponde con las lista de items. En el método
getItemCount le pasamos el numero de  items que tiene que pintar. En el método onBindViewHolder el
ViewHolder nos permite blindear cada elemento utilizando su posicion en la lista. En la inner class
blindeamos y cargamos la imagen a través de un método propio suporteado por la librería Picasso para
cargar imágenes. Aquí también utilizamos una interface Callback que nos permite saber en que item exactamente
hemos tocado, para así poder devolver su contenido.
 */

class GridAdapterLigas (private var todasLigas: List<ModeloLiga>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var callback: LigasClickCallback

    interface LigasClickCallback {
        fun clickLiga(liga: ModeloLiga)
    }

    fun setCallback (call: LigasClickCallback) {
        callback = call
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_futbol, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return todasLigas.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = GridListFutbolBinding.bind(itemView)

        init{
            itemView.setOnClickListener{
                val tituloLiga = todasLigas[absoluteAdapterPosition]
                callback.clickLiga(tituloLiga)
            }
        }

        fun bind(liga: ModeloLiga) {
            Utils.loadImage(liga.imagen ?: " ", binding.imgGridLigas)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val ligas = todasLigas[position]
                holder.bind(ligas)
            }
        }
    }
}