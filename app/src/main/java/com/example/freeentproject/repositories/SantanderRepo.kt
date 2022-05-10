package com.example.freeentproject.repositories
import com.example.freeentproject.domain.models.ModeloSantander
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
Repositorio que nos permite combinar un método síncrono propio con uno asíncrono creado para
firebase, para así obtener todos los partidos de la liga Santander dos formas diferentes, en la
primera como ya dijimos en los casos de uso el numero partidos de la liga Santander estará limitado,
en el segundo nos traeremos todos partidos de la liga Santander sin excepción. En Kotlin para hacer
estas llamadas debemos usar las Corrutinas, esto es un ejemplo de ello. Conectamos a la base de
datos de firebase, creamos un método que nos devuelve una lista de objetos ModeloSantander, creamos
la lista donde nos traemos las imagenes y url de los partidos de la liga Santander que existan como
documento dentro de la coleccion "resumenSantander" para formar el numero de objetos posibles (uno
de los casos 6 exactamente) del tipo ModeloSantander. Finalmente retornamos la lista.
 */

class SantanderRepo @Inject constructor(){

    private val db = FirebaseFirestore.getInstance()

    suspend fun getInicioSantanderCorutinas() : List<ModeloSantander> {
        val inicio : List<ModeloSantander> =
            db.collection("resumenSantander").limit(6).get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                ModeloSantander(imagen!!, url!!)
            }
        return inicio
    }

    suspend fun getSantanderGridFragmentCorutinas() : List<ModeloSantander> {
        val inicio : List<ModeloSantander> =
            db.collection("resumenSantander").get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                ModeloSantander(imagen!!, url!!)
            }
        return inicio
    }
}