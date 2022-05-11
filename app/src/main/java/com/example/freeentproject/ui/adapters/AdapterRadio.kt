package com.example.freeentproject.ui.adapters
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.ListRadioBinding
import com.example.freeentproject.domain.models.ModeloHijoRadio
import com.example.freeentproject.domain.models.ModeloLiga
import com.example.freeentproject.domain.models.ModeloRadio
import com.example.freeentproject.ui.activitys.ExoPlayerPlayPeli
import com.example.freeentproject.ui.fragments.RadioFragment
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de cadenas de radio. En el metodo onCreateViewHolder le
pasamos el layaout de corresponde con las lista de items. En el método getItemCount le pasamos el
numero de  items que tiene que pintar. En el método onBindViewHolder el ViewHolder nos permite
blindear cada elemento utilizando su posicion en la lista. En la inner class blindeamos y cargamos
la imagen a través de un método propio suporteado por la librería Picasso para cargar imágenes.
 */

class AdapterRadio (var items: ModeloHijoRadio): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.list_radio, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return items.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(items.list[position])
                holder.binding.imgRadio.setOnClickListener {

                }
            }
        }
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListRadioBinding.bind(itemView)

        fun bind(radio: ModeloRadio) {

            Utils.loadImage(radio.imagen ?: " ", binding.imgRadio)
        }
    }
}