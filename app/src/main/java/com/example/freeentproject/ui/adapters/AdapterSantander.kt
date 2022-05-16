package com.example.freeentproject.ui.adapters
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.ListSantanderBinding
import com.example.freeentproject.domain.models.ModeloHijoSantander
import com.example.freeentproject.domain.models.ModeloSantander
import com.example.freeentproject.ui.activitys.ExoPlayerPlayResumen
import com.example.freeentproject.utils.Utils

/*
Adaptador que nos permite mostrar una lista de partidos de la liga Santander. En el metodo
onCreateViewHolder le pasamos el layaout de corresponde con las lista de items. En el método
getItemCount le pasamos el numero de  items que tiene que pintar. En el método onBindViewHolder el
ViewHolder nos permite blindear cada elemento utilizando su posicion en la lista. En la inner class
blindeamos y cargamos la imagen a través de un método propio suporteado por la librería Picasso para
cargar imágenes.
 */

class AdapterSantander(var items: ModeloHijoSantander): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.list_santander, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return items.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(items.list[position])
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ListSantanderBinding.bind(itemView)

        fun bind(resumen: ModeloSantander) {

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, ExoPlayerPlayResumen::class.java)
                intent.putExtra("url", resumen.url!!)
                context.startActivity(intent)
            }

            Utils.loadImage(resumen.imagen ?:" ", binding.imgSantander)
        }
    }
}