package com.example.freeentproject.ui.adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freeentproject.R
import com.example.freeentproject.databinding.GridFavListPelisBinding
import com.example.freeentproject.databinding.GridListPeliBinding
import com.example.freeentproject.domain.models.ModeloPeli
import com.example.freeentproject.utils.Utils
import com.google.firebase.firestore.FirebaseFirestore

class AdapterPelisFav  (private var pelisFav: List<ModeloPeli>): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private lateinit var callback: PelisClickCallback

    interface PelisClickCallback {
        fun clickPeliFav (pelicula: ModeloPeli)
    }

    fun setCallback (call: PelisClickCallback) {
        callback = call
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.grid_fav_list_pelis, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val tvs = pelisFav[position]
                holder.bind(tvs)
            }
        }
    }

    override fun getItemCount(): Int {
        return pelisFav.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding= GridFavListPelisBinding.bind(itemView)
        init {
            itemView.setOnClickListener {
                val tituloPeli = pelisFav[absoluteAdapterPosition]
                callback.clickPeliFav(tituloPeli)
            }

            binding.deleteBut.setOnClickListener {
                deletePeli(pelisFav[absoluteAdapterPosition].titulo!!)
            }
        }

        fun bind(cine: ModeloPeli) {
            Utils.loadImage((cine.imagen ?: "" ), binding.imgFavPeli)
        }

        private fun deletePeli(titulo: String) {
            val db = FirebaseFirestore.getInstance()
            db.collection("pelisFav")
                .document(titulo)
                .delete()
                .addOnSuccessListener { Log.d(ContentValues.TAG, "delete correctly") }
                .addOnFailureListener { Log.d(ContentValues.TAG,"error delete") }
        }
    }
}