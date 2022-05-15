package com.example.freeentproject.repositories
import com.example.freeentproject.domain.models.ModeloPeli
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
Repositorio que nos permite combinar un método síncrono propio con uno asíncrono creado para
firebase, para así obtener todas las películas dos formas diferentes, en la primera como ya dijimos
en los casos de uso el numero de películas estará limitado, en el segundo nos traeremos todas las
peliculas sin excepción. En Kotlin para hacer estas llamadas debemos usar las Corrutinas, esto es un
ejemplo de ello. Conectamos a la base de datos de firebase, creamos un metodo que nos devuelve una
lista de objetos ModeloPeli, creamos la lista donde nos traemos las imagenes, url, titulos y
descripciones de peliculas que existan como documento dentro de la coleccion "peliculas" para
formar el numero de objetos posibles (uno de los casos 6 exactamente) del tipo ModeloPeli.
Finalmente retornamos la lista.
 */

class PelisRepo @Inject constructor()  {

    private val db = FirebaseFirestore.getInstance()

    suspend fun getInicioPelisCorutinas() : List<ModeloPeli> {
        val inicio : List<ModeloPeli> =
            db.collection("peliculas").limit(6).get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                val titulo = it.getString("titulo")
                val descripcion = it.getString("descripcion")
                val lenguaje = it.getString("lenguaje")
                val duracion = it.getString("duracion")
                ModeloPeli(imagen!!, url!!, titulo!!, descripcion!!, lenguaje!!, duracion!!)
            }
        return inicio
    }

    suspend fun getPelisGridFragmentCorutinas() : List<ModeloPeli> {
        val inicio : List<ModeloPeli> =
            db.collection("peliculas").get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                val titulo = it.getString("titulo")
                val descripcion = it.getString("descripcion")
                val lenguaje = it.getString("lenguaje")
                val duracion = it.getString("duracion")
                ModeloPeli(imagen!!, url!!, titulo!!, descripcion!!, lenguaje!!, duracion!!)
            }
        return inicio
    }

    suspend fun getPelisFavoritasFragmentCorutinas() : List<ModeloPeli> {
        val inicio : List<ModeloPeli> =
            db.collection("pelisFav").get().await().documents.map {
                val imagen = it. getString("imagen")
                val url = it.getString("url")
                val titulo = it.getString("titulo")
                val descripcion = it.getString("descripcion")
                val lenguaje = it.getString("lenguaje")
                val duracion = it.getString("duracion")
                ModeloPeli(imagen!!, url!!, titulo!!, descripcion!!, lenguaje!!, duracion!!)
            }
        return inicio
    }

    suspend fun deletePeli(titulo: String){
        db.collection("pelisFav").document(titulo).delete().await()
    }
}