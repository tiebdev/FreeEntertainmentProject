package com.example.freeentproject.ui.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.freeentproject.R
import com.example.freeentproject.databinding.RecyclerPeliBinding
import com.example.freeentproject.databinding.RecyclerRadioBinding
import com.example.freeentproject.databinding.RecyclerSantanderBinding
import com.example.freeentproject.databinding.RecyclerTvBinding
import com.example.freeentproject.domain.models.*

/*
Adaptador Padre que nos permite mostrar una lista vertical que contiene otras listas horizontales.
Creamos cuatro variables del tipo correspondiente para cada ModeloHijo y creamos una ArrayList vacía
del tipo ModeloPadre que llenará con las listas formadas a partir de estos modelos.
Creamos cuatro métodos para generar las listas correspondientes a cada modelo, a partir de los
objetos que entran por el Observe{} en el Fragment.
 */
class AdapterPadre (): RecyclerView.Adapter<ViewHolder>() {

    private var todo = ArrayList<ModeloPadre>()
    private var pelis : ModeloHijoPeli? = null
    private var radios : ModeloHijoRadio? = null
    private var tvs : ModeloHijoTv? = null
    private var santander : ModeloHijoSantander? = null

    fun addPelis(peli : List<ModeloPeli>) {
        if (peli.isNullOrEmpty()) return
        pelis = ModeloHijoPeli(0, peli)
        todo.add(pelis!!)
    }

    fun addTv(tv : List<ModeloTv>){
        if (tv.isNullOrEmpty()) return
        tvs = ModeloHijoTv (1, tv)
        todo.add(tvs!!)
    }

    fun addRadio(radio : List<ModeloRadio>) {
        if (radio.isNullOrEmpty()) return
        radios = ModeloHijoRadio(2, radio)
        todo.add(radios!!)
    }

    fun addSantander(primera: List<ModeloSantander>){
        if (primera.isNullOrEmpty()) return
        santander = ModeloHijoSantander(3, primera)
        todo.add(santander!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var vista: RecyclerView.ViewHolder? = null

        when (viewType) {

            0 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_peli, parent, false)
                vista = ViewHolder(view)
            }

            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_tv, parent, false)
                vista = ViewHolder1(view)

            }

            2 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_radio, parent, false)
                vista = ViewHolder2(view)
            }

            3 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_santander, parent, false)
                vista = ViewHolder3(view)
            }
        }
        return vista!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val peli = todo[position] as ModeloHijoPeli
                holder.bind(peli)
            }
            is ViewHolder1 -> {
                val tv = todo[position] as ModeloHijoTv
                holder.bind(tv)
            }
            is ViewHolder2 -> {
                val radio = todo[position] as ModeloHijoRadio
                holder.bind(radio)
            }
            is ViewHolder3 -> {
                val resumen = todo[position] as ModeloHijoSantander
                holder.bind(resumen)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerPeliBinding.bind(itemView)

        fun bind(peli: ModeloHijoPeli) {

            val recyclerPeli = AdapterPeli(peli)
            binding.recyclerPelis.layoutManager = LinearLayoutManager(binding.root.context,
                LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerPelis.adapter = recyclerPeli
        }
    }

    inner class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerTvBinding.bind(itemView)

        fun bind(tv: ModeloHijoTv) {
            val recyclerTv = AdapterTv(tv)
            binding.recyclerTv.layoutManager = LinearLayoutManager(binding.root.context,
                LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerTv.adapter = recyclerTv
        }
    }

    inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerRadioBinding.bind(itemView)

        fun bind(radio: ModeloHijoRadio) {
            val recyclerRadio = AdapterRadio(radio)
            binding.recyclerRadio.layoutManager = LinearLayoutManager(binding.root.context,
                LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerRadio.adapter = recyclerRadio
        }
    }

    inner class ViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = RecyclerSantanderBinding.bind(itemView)

        fun bind(santander: ModeloHijoSantander) {
            val recyclerRadio = AdapterSantander(santander)
            binding.recyclerSmartbank.layoutManager = LinearLayoutManager(binding.root.context,
                LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerSmartbank.adapter = recyclerRadio
        }
    }

    override fun getItemViewType(position: Int): Int {
        return todo[position].viewType
    }

    override fun getItemCount(): Int {
        return todo.size
    }
}