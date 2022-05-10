package com.example.freeentproject.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.GridListRadioBinding
import com.example.freeentproject.domain.models.ModeloRadio
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de cadenas de radio existentes en la bd. En el metodo
onCreateViewHolder le pasamos el layaout de corresponde con las lista de items. En el método
getItemCount le pasamos el numero de  items que tiene que pintar. En el método onBindViewHolder el
ViewHolder nos permite blindear cada elemento utilizando su posicion en la lista. En la inner class
blindeamos y cargamos la imagen a través de un método propio suporteado por la librería Picasso para
cargar imágenes. Aquí también utilizamos una interface Callback que nos permite saber en que item exactamente
hemos tocado, para así poder devolver su contenido.
 */

class GridAdapterRadio (private var todasRadios: List<ModeloRadio>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var callback: RadiosClickCallback

    interface RadiosClickCallback {
        fun clickRadio(radio: ModeloRadio)
    }

    fun setCallback (call: RadiosClickCallback) {
        callback = call
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_radio, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val radios = todasRadios[position]
                holder.bind(radios)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = GridListRadioBinding.bind(itemView)

        init{
            itemView.setOnClickListener{
                val tituloRadio = todasRadios[absoluteAdapterPosition]
                callback.clickRadio(tituloRadio)
            }
        }

        fun bind(radio: ModeloRadio) {
            Utils.loadImage(radio.imagen ?: " ", binding.imgGridRadio)
        }
    }

    override fun getItemCount(): Int {
        return todasRadios.size
    }
}
