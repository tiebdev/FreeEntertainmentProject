package com.example.freeentproject.repositories
import com.example.freeentproject.domain.models.ModeloSmartbank
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
Repositorio que nos permite combinar un método síncrono propio con uno asíncrono creado para
firebase, para así obtener todos los partidos de la liga Smartbank dos formas diferentes, en la
primera como ya dijimos en los casos de uso el numero partidos de la liga Smartbank estará limitado,
en el segundo nos traeremos todos partidos de la liga Smartbank sin excepción. En Kotlin para hacer
estas llamadas debemos usar las Corrutinas, esto es un ejemplo de ello. Conectamos a la base de
datos de firebase, creamos un método que nos devuelve una lista de objetos ModeloSmartbank, creamos
la lista donde nos traemos las imagenes y url de los partidos de la liga Smartbank que existan como
documento dentro de la coleccion "resumenSmartbank" para formar el numero de objetos posibles (uno
de los casos 6 exactamente) del tipo ModeloSmartbank. Finalmente retornamos la lista.
 */

class SmartbankRepo @Inject constructor() {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getSantanderGridFragmentCorutinas() : List<ModeloSmartbank> {
        val inicio : List<ModeloSmartbank> =
            db.collection("resumenSmartbank").get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                ModeloSmartbank(imagen!!, url!!)
            }
        return inicio
    }
}