package com.example.freeentproject.ui.adapters
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.GridListTvBinding
import com.example.freeentproject.domain.models.ModeloTv
import com.example.freeentproject.ui.activitys.ExoPlayerPlayTv
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de cadenas de tv existentes en la bd. En el metodo
onCreateViewHolder le pasamos el layaout de corresponde con las lista de items. En el método
getItemCount le pasamos el numero de  items que tiene que pintar. En el método onBindViewHolder el
ViewHolder nos permite blindear cada elemento utilizando su posicion en la lista. En la inner class
blindeamos y cargamos la imagen a través de un método propio suporteado por la librería Picasso para
cargar imágenes. Aquí también mandamos la url que contiene la cadena de tv a través de un intent a
la Activity que la reproduce.
 */

class GridAdapterTv (private var todasTv: List<ModeloTv>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_tv, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val tvs = todasTv[position]
                holder.bind(tvs)
            }
        }
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = GridListTvBinding.bind(itemView)

        fun bind(tv: ModeloTv) {

            itemView.setOnClickListener {

                val context = itemView.context
                val intent = Intent(context, ExoPlayerPlayTv::class.java)
                intent.putExtra("url", tv.url!!)
                context.startActivity(intent)
            }

            Utils.loadImage(tv.imagen ?: " ", binding.imgGridTv)
        }
    }

    override fun getItemCount(): Int {
        return todasTv.size
    }
}
