package com.example.freeentproject.repositories
import com.example.freeentproject.domain.models.ModeloLiga
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
Repositorio que nos permite combinar un método síncrono propio con uno asíncrono creado para
firebase, para así obtener todas las ligas que existan en la base de datos. En Kotlin para hacer
estas llamadas debemos usar las Corrutinas, esto es un ejemplo de ello. Conectamos a la base de
datos de firebase, creamos un metodo que nos devuelve una lista de objetos ModeloLiga, creamos la
lista donde nos traemos todos las imagenes y nombres de ligas que existan como documento dentro de
la coleccion "ligas" para formar el numero de objetos posibles del tipo ModeloLiga. Finalmente
retornamos la lista.
 */
class LigasRepo @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getLigasFragmentCorutinas() : List<ModeloLiga> {
        val inicio : List<ModeloLiga> =
            db.collection("ligas").get().await().documents.map {
                val imagen = it. getString("imagen")
                val nombre = it.getString("nombre")
                ModeloLiga(imagen!!, nombre!!)
            }
        return inicio
    }
}