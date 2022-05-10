package com.example.freeentproject.repositories
import com.example.freeentproject.domain.models.ModeloTv
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
Repositorio que nos permite combinar un método síncrono propio con uno asíncrono creado para
firebase, para así obtener todas las cadenas de tv dos formas diferentes, en la primera como ya
dijimos en los casos de uso el numero de cadenas de tv estará limitado, en el segundo nos
traeremos todas las cadenas de tv sin excepción. En Kotlin para hacer estas llamadas debemos usar
las Corrutinas, esto es un ejemplo de ello. Conectamos a la base de datos de firebase, creamos un
método que nos devuelve una lista de objetos ModeloTv, creamos la lista donde nos traemos las
imagenes y url de las cadenas de tv que existan como documento dentro de la coleccion "tdt" para
formar el numero de objetos posibles (uno de los casos 8 exactamente) del tipo ModeloTv.
Finalmente retornamos la lista.
 */

class TvRepo @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getInicioTvCorutinas() : List<ModeloTv> {
        val inicio : List<ModeloTv> =
            db.collection("tdt").limit(8).get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                ModeloTv(imagen!!, url!!)
            }
        return inicio
    }

    suspend fun getTvGridFragmentCorutinas() : List<ModeloTv> {
        val inicio : List<ModeloTv> =
            db.collection("tdt").get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                ModeloTv(imagen!!, url!!)
            }
        return inicio
    }
}