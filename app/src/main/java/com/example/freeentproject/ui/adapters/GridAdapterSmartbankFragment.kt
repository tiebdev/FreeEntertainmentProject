package com.example.freeentproject.ui.adapters
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.GridListSmartbankBinding
import com.example.freeentproject.domain.models.ModeloSmartbank
import com.example.freeentproject.ui.activitys.ExoPlayerPlayResumen
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de partidos existentes en la bd. En el metodo
onCreateViewHolder le pasamos el layaout de corresponde con las lista de items. En el método
getItemCount le pasamos el numero de  items que tiene que pintar. En el método onBindViewHolder el
ViewHolder nos permite blindear cada elemento utilizando su posicion en la lista. En la inner class
blindeamos y cargamos la imagen a través de un método propio suporteado por la librería Picasso para
cargar imágenes. Aquí también mandamos la url que contiene el resumen del partido a través de un
intent a la Activity que lo reproduce.
 */

class GridAdapterSmartbankFragment (private var todasSmartbank: List<ModeloSmartbank>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = GridListSmartbankBinding.bind(itemView)

        fun bind(smartbank: ModeloSmartbank) {

            itemView.setOnClickListener {

                val context = itemView.context
                val intent = Intent(context, ExoPlayerPlayResumen::class.java)
                intent.putExtra("url", smartbank.url!!)
                context.startActivity(intent)
            }

            Utils.loadImage(smartbank.imagen ?: " ", binding.imgGridSmartbank)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_smartbank , parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val tvs = todasSmartbank[position]
                holder.bind(tvs)
            }
        }
    }

    override fun getItemCount(): Int {
        return todasSmartbank.size
    }
}