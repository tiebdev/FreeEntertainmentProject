package com.example.freeentproject.repositories
import com.example.freeentproject.domain.models.ModeloRadio
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
Repositorio que nos permite combinar un método síncrono propio con uno asíncrono creado para
firebase, para así obtener todas las cadenas de radio dos formas diferentes, en la primera como ya
dijimos en los casos de uso el numero de cadenas de radio estará limitado, en el segundo nos
traeremos todas las cadenas de radio sin excepción. En Kotlin para hacer estas llamadas debemos usar
las Corrutinas, esto es un ejemplo de ello. Conectamos a la base de datos de firebase, creamos un
método que nos devuelve una lista de objetos ModeloRadio, creamos la lista donde nos traemos las
imagenes y url de cadenas de radio que existan como documento dentro de la coleccion "radio" para
formar el numero de objetos posibles (uno de los casos 8 exactamente) del tipo ModeloRadio. Finalmente retornamos la lista.
 */

class RadioRepo @Inject constructor(){
    private val db = FirebaseFirestore.getInstance()

    suspend fun getInicioRadioCorutinas() : List<ModeloRadio> {
        val inicio : List<ModeloRadio> =
            db.collection("radio").limit(8).get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                ModeloRadio(imagen!!, url!!)
        }
        return inicio
    }

    suspend fun getRadiosGridFragmentCorutinas() : List<ModeloRadio> {
        val inicio : List<ModeloRadio> =
            db.collection("radio").get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                ModeloRadio(imagen!!, url!!)
            }
        return inicio
    }
}